package compression;

import java.util.ArrayList;

public class LZ78CompressionAlgorithm implements CompressionAlgorithm {

    @Override
    public void compress(String content, BinaryOutputStream out){
        char[] charContent = content.toCharArray();
        ArrayList<String> dict = new ArrayList<>();
        String string = "";
        for (char c : charContent) {
            String aux = string + c;
            if (dict.contains(aux)) {
                string = aux;
            } else {
                out.write(dict.indexOf(string), 24);
                out.write(c);
                dict.add(aux);
                string = "";
            }
        }
    }
}
