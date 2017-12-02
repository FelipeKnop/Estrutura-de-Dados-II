package compression;

import javafx.util.Pair;

import java.util.ArrayList;

public class LZ77CompressionAlgorithm implements CompressionAlgorithm {

    private static final int WINDOW_SIZE = 15;
    private static final int BUFFER_SIZE = 15;

    @Override
    public byte[] compress(String content) {
        ArrayList<Boolean> output = new ArrayList<>();
        int i = 0;
        while (i < content.length()) {
            Pair<Integer, Integer> match = findLongestMatch(content, i);

            if (match != null) {
                int bestMatchDistance = match.getKey();
                int bestMatchLength = match.getValue();

                output.add(true);
                output.addAll(fromBytes((char) (bestMatchDistance >> 4)));
                output.addAll(fromBytes((char) (((bestMatchDistance & 0xf) << 4) | bestMatchLength)));

                i += bestMatchLength;
            } else {
                output.add(false);
                output.addAll(fromBytes(content.charAt(i)));

                i++;
            }
        }

        for (i = output.size() % 8; i < 8; i++)
            output.add(false);

        return toByteArray(output);
    }

    private byte[] toByteArray(ArrayList<Boolean> list) {
        byte[] result = new byte[list.size() / 8];
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < 8; j++)
                if (list.get(i * 8 + j))
                    result[i] |= (128 >> j);
        return result;
    }

    private Pair<Integer, Integer> findLongestMatch(String content, int position) {
        int endOfBuffer = Math.min(position + BUFFER_SIZE, content.length() + 1);

        int bestMatchDistance = -1;
        int bestMatchLength = -1;

        for (int i = position + 2; i < endOfBuffer; i++) {
            int startIndex = Math.max(0, position - WINDOW_SIZE);
            String substring = content.substring(position, i);

            for (int j = startIndex; j < position; j++) {
                int repetitions = substring.length() / (position - j);

                int last = substring.length() % (position - j);

                String matchedString = new String(new char[repetitions]).replace("\0", content.substring(j, position)) + content.substring(j, j + last);

                if (matchedString.equals(substring) && substring.length() > bestMatchLength) {
                    bestMatchDistance = position - j;
                    bestMatchLength = substring.length();
                }
            }
        }

        if (bestMatchDistance > 0 && bestMatchLength > 0)
            return new Pair<>(bestMatchDistance, bestMatchLength);

        return null;
    }

    private ArrayList<Boolean> fromBytes(char c) {
        ArrayList<Boolean> result = new ArrayList<>();
        String binaryString = Integer.toBinaryString((int) c);
        for (char bit : binaryString.toCharArray())
            result.add(bit == '1');
        return result;
    }
}
