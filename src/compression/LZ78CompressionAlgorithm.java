package compression;

import java.util.ArrayList;
import java.util.List;

public class LZ78CompressionAlgorithm implements CompressionAlgorithm {

    // TODO
    @Override
    public byte[] compress(String content){
        int i, j, lastIndex;
        boolean done = false;
        String output = "";
        for(i=0;i<content.length();i = i+j){
            j=0;
            lastIndex = 0;
            while(!done){
                int aux;
                aux = findString(content.substring(i,j));
                if(aux==0)
                    done = true;
                else{
                    lastIndex = aux;
                }
                j++;
            }
            dict.add(new entry(lastIndex, content.substring(i,j-1)));
            output.concat(Integer.valueOf(lastIndex).toString());
            output.concat(content.substring(i,j-1));
        }
        return output.getBytes();
    }

    public class entry{
        private String data;
        private int index;
        public entry(int index, String data){
            this.data = data;
            this.index = index;
        }
    }

    private List<entry> dict = new ArrayList<>();

    public int findString(String data){
        for(int i = 0; i<dict.size(); i++){
            if(data == dict.get(i).data)
                return i;
        }
        return 0;
    }



}
