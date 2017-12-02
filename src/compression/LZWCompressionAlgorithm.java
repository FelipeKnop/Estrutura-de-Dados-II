package compression;

import java.util.HashMap;
import java.util.Map;

public class LZWCompressionAlgorithm implements CompressionAlgorithm {

    // TODO
    @Override
    public byte[] compress(String content) {
        char c ;
        //Iniciando dicionario com caracteres basicos da tabela ASCII
        Map<String,Integer>  dicionario = new HashMap<>();
        for(int z=0; z<=255; z++)
        {
            c = (char) z;
            dicionario.put(new String(""+c),z);
        }
        String oldReader="" + content.charAt(0);
        String reader = "" ;
        String output = "";
        int count = 1, i = 1;
        int tam = content.length();
        while(i < tam)
        {
            reader = oldReader + content.charAt(i);
            if (reader.length() > 1)
            {
                if(!dicionario.containsKey(reader))
                {
                    dicionario.put(reader,255+count);
                    count++;
                    output = output +  dicionario.get(oldReader);
                    oldReader  = "" + reader.charAt(reader.length()-1);
                }
                else oldReader = reader;

            }
           i++;

        }

        return output.getBytes();
    }
}
