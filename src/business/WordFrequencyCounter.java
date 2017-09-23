package business;

import hash.DJB2Hash;
import models.Tweet;
import models.WordFrequency;
import sort.MergeSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordFrequencyCounter {

    private static final int TABLE_SIZE = 100000;

    private WordFrequencyHashingMethod hashingMethod;

    private ArrayList<WordFrequency> wordFrequencies;

    public WordFrequencyCounter() {
        hashingMethod = new WordFrequencyHashingMethod(TABLE_SIZE);
    }

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

    public void printMostFrequentWords(int numWords) {
        assert this.wordFrequencies != null;
        for (int i = 0; i < numWords && i < wordFrequencies.size(); i++) {
            WordFrequency wordFrequency = wordFrequencies.get(i);
            System.out.println(wordFrequency.getCount() + " " + wordFrequency.getWord());
        }
    }

    private class WordFrequencyHashingMethod {

        private final ArrayList<ArrayList<WordFrequency>> hashTable;
        private final DJB2Hash hashingMethod;

        WordFrequencyHashingMethod(int tableSize) {
            this.hashTable = new ArrayList<>(Collections.nCopies(tableSize, null));
            this.hashingMethod = new DJB2Hash(tableSize);
        }

        ArrayList<ArrayList<WordFrequency>> getHashTable() {
            return hashTable;
        }

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
