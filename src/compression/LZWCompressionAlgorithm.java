package compression;

import java.util.HashMap;
import java.util.Map;

public class LZWCompressionAlgorithm implements CompressionAlgorithm {

    // TODO
    @Override
    public void compress(String content, BinaryOutputStream output) {
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
        int count = 1, i = 1;
        int tam = content.length();
        while(i < tam)
        {

            if( content.charAt(i)<=255 ) {   //Verifica se caracter pertence a valores basicos da tabela ASCII
                reader = oldReader + content.charAt(i);
                if (reader.length() > 1) {
                    if (!dicionario.containsKey(reader)) {
                        dicionario.put(reader, 255 + count);
                        count++;
                        output.write(dicionario.get(oldReader));
                        oldReader = "" + content.charAt(i);
                    } else oldReader = reader;

                }
            }
           i++;

        }


    }
}
