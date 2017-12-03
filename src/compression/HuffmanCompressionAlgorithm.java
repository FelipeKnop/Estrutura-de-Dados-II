package compression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanCompressionAlgorithm implements CompressionAlgorithm {

    @Override
    public void compress(String content, BinaryOutputStream out) {
        char[] charContent = content.toCharArray();

        int[] frequencies = new int[256];

        for (char c : charContent)
            if (c < 256)
                frequencies[c]++;

        PriorityQueue<HuffmanNode> trees = new PriorityQueue<>();

        for (int i = 0; i < frequencies.length; i++)
            if (frequencies[i] > 0)
                trees.offer(new LeafNode(frequencies[i], (char) i));

        while (trees.size() > 1) {
            HuffmanNode a = trees.poll();
            HuffmanNode b = trees.poll();

            trees.offer(new InternalNode(a, b));
        }

        HuffmanNode tree = trees.poll();
        HashMap<Character, ArrayList<Boolean>> codes = new HashMap<>();
        getCodes(tree, codes, new ArrayList<>());

        for (char c : charContent)
            for (boolean bit : codes.getOrDefault(c, new ArrayList<>()))
                out.write(bit);
    }

    private void getCodes(HuffmanNode root, HashMap<Character, ArrayList<Boolean>> codes, ArrayList<Boolean> buffer) {
        if (root instanceof LeafNode) {
            LeafNode node = (LeafNode) root;

            codes.put(node.value, new ArrayList<>(buffer));
        } else {
            InternalNode node = (InternalNode) root;

            buffer.add(false);
            getCodes(node.left, codes, buffer);
            buffer.remove(buffer.size() - 1);

            buffer.add(true);
            getCodes(node.right, codes, buffer);
            buffer.remove(buffer.size() - 1);
        }
    }

    private abstract class HuffmanNode implements Comparable<HuffmanNode> {

        private final int frequency;

        private HuffmanNode(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return frequency - o.frequency;
        }
    }

    private class InternalNode extends HuffmanNode {

        private final HuffmanNode left, right;

        private InternalNode(HuffmanNode left, HuffmanNode right) {
            super(left.frequency + right.frequency);
            this.left = left;
            this.right = right;
        }
    }

    private class LeafNode extends HuffmanNode {

        private final char value;

        private LeafNode(int frequency, char value) {
            super(frequency);
            this.value = value;
        }
    }
}
