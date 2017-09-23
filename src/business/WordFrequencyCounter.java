package business;

import hash.DJB2Hash;
import models.Tweet;
import models.WordFrequency;
import sort.MergeSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que cuida da lógica de contagem de palavras em uma lista de Tweets.
 */
public class WordFrequencyCounter {

    /**
     * Tamanho da Tabela Hash.
     */
    private static final int TABLE_SIZE = 100000;

    /**
     * Método de hash para contagem de palavras.
     */
    private WordFrequencyHashingMethod hashingMethod;

    /**
     * Lista com as frequências de cada palavra.
     */
    private ArrayList<WordFrequency> wordFrequencies;

    public WordFrequencyCounter() {
        hashingMethod = new WordFrequencyHashingMethod(TABLE_SIZE);
    }

    /**
     * A partir de uma lista de Tweets, chama o método <code>getWords</code>
     * para obter uma lista de todas as palavras encontradas nos Tweets e as
     * insere na Tabela Hash. Após isso, obtém todas as palavras com suas
     * frequências e as ordena utilizando o método {@link MergeSort}, guardando
     * o resultado na variável <code>wordFrequencies</code>.
     * @param tweets Lista de Tweets
     */
    public void countWords(List<Tweet> tweets) {
        List<String> words = getWords(tweets);
        words.forEach(hashingMethod::insert);

        ArrayList<ArrayList<WordFrequency>> hashTable = hashingMethod.getHashTable();
        List<WordFrequency> wordFrequencies = new ArrayList<>();
        hashTable.forEach(list -> {
            if (list != null && list.size() != 0)
                wordFrequencies.addAll(list);
        });

        MergeSort mergeSort = new MergeSort();
        this.wordFrequencies = (ArrayList<WordFrequency>) mergeSort.sort(wordFrequencies);
    }

    /**
     * Método que retorna todas as palavras encontrada em uma lista
     * de Tweets dada. Para isso, pega o texto de cada Tweet, separa
     * o texto em segmentos divididos pelo caracter de espaço e adiciona
     * na lista de palavras somente aqueles segmentos que contém apenas
     * letras.
     * @param tweets Lista de Tweets
     * @return Lista de palavras
     */
    private List<String> getWords(List<Tweet> tweets) {
        List<String> words = new ArrayList<>();
        tweets.forEach(tweet -> {
            String[] split = tweet.getTweetText().split(" ");
            for (String word : split)
                if (word.matches("[a-z]\\w+"))
                    words.add(word.toLowerCase());
        });
        return words;
    }

    /**
     * Imprime as <code>numWords</code> palavras mais frequentes,
     * assim como suas frequências.
     * @param numWords Número de palavras a serem impressas com
     *                 suas frequências
     */
    public void printMostFrequentWords(int numWords) {
        assert this.wordFrequencies != null;
        for (int i = 0; i < numWords && i < wordFrequencies.size(); i++) {
            WordFrequency wordFrequency = wordFrequencies.get(i);
            System.out.println(wordFrequency.getCount() + " " + wordFrequency.getWord());
        }
    }

    /**
     * Classe que implementa a lógica de Tabela Hash para a contagem de palavras.
     * Utiliza um método semelhante ao Encadeamento Separado.
     */
    private class WordFrequencyHashingMethod {

        /**
         * Tabela Hash.
         */
        private final ArrayList<ArrayList<WordFrequency>> hashTable;

        /**
         * Método de hashing DJB2.
         */
        private final DJB2Hash hashingMethod;

        WordFrequencyHashingMethod(int tableSize) {
            this.hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
            this.hashingMethod = new DJB2Hash(tableSize);
        }

        ArrayList<ArrayList<WordFrequency>> getHashTable() {
            return hashTable;
        }

        /**
         * Método que insere palavras na tabela hash.
         * Se aquela palavra já existe na tabela, aumenta seu contador
         * de frequência em 1.
         * @param word Palavra a ser inserida na Tabela Hash
         */
        void insert(String word) {
            int hash = hashingMethod.hashingFunction(word);

            if (hashTable.get(hash) == null)
                hashTable.set(hash, new ArrayList<>());

            ArrayList<WordFrequency> list = hashTable.get(hash);
            boolean hasWord = false;

            for (WordFrequency wordFrequency : list) {
                if (wordFrequency.getWord().equals(word)) {
                    hasWord = true;
                    wordFrequency.incrementCount();
                }
            }
            if (!hasWord) list.add(new WordFrequency(word));
        }
    }
}
