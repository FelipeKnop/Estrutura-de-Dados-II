package compression;

import javafx.util.Pair;

public class LZ77CompressionAlgorithm implements CompressionAlgorithm {

    private static final int WINDOW_SIZE = 63;
    private static final int BUFFER_SIZE = 3;

    @Override
    public void compress(String content, BinaryOutputStream out) {
        int i = 0;
        while (i < content.length()) {
            Pair<Integer, Integer> match = findLongestMatch(content, i);

            if (match != null) {
                int bestMatchDistance = match.getKey();
                int bestMatchLength = match.getValue();

                out.write(true);
                out.write(bestMatchDistance, 6);
                out.write(bestMatchLength, 2);

                i += bestMatchLength;
            } else {
                out.write(false);
                out.write(content.charAt(i));

                i++;
            }
        }
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
}
