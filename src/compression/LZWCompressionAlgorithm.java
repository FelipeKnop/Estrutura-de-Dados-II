package compression;

import java.util.ArrayList;

public class LZWCompressionAlgorithm implements CompressionAlgorithm {

    @Override
    public void compress(String content, BinaryOutputStream out) {
        String string = String.valueOf(content.charAt(0));
        content = content.substring(1);
        char[] charContent = content.toCharArray();
        ArrayList<String> dict = new ArrayList<>();
        for (char c : charContent) {
            String aux = string + c;
            if (dict.contains(aux)) {
                string = aux;
            } else {
                out.write(dict.indexOf(string), 24);
                dict.add(aux);
                string = String.valueOf(c);
            }
        }
    }
}
