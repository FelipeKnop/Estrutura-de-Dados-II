package hash;

/**
 * Classe que extende a classe abstrata {@link HashingAlgorithm HashingAlgorithm} utilizando o algoritmo
 */
public class wordHash{
    /**
     *
     * @return Hash gerado pela função de hashing a partir do valor recebido
     */
    public static int hashingFunction(String word) {
        long hash = 5381;
        for(int i=0; i<word.length();i++){
            hash = ((hash << 5) + hash) + (long)word.charAt(i); /* hash * 33 + c */
        }
        return (int)hash;
    }
}
