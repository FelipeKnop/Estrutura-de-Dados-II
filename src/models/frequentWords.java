package models;

import business.TweetFileReader;
import hash.wordHash;
import sort.MergeSort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class frequentWords {

    public static void mostFrequentWords(int numTweets, int numWords, TweetFileReader tweetFileReader){
        wordTable[] wordHashTable = new wordTable[20000];
        List<Tweet> tweets = null;
        ArrayList<String> words = new ArrayList<>();
        tweets = tweetFileReader.getTweets();
        int i;
        int maxStep = 1000000/numTweets;
        Random stepSelector = new Random();
        for(i=0;i<20000;i++)
            wordHashTable[i] = new wordTable();
        i=0;
        for(Iterator<Tweet> tweet = tweets.iterator(); tweet.hasNext() && (i < numTweets);) {
            for(int j=0;j<stepSelector.nextInt(maxStep)-1;j++)
                tweet.next();
            String tweetWords[] = tweet.next().getTweetText().split("\\W+");
            for(int j=0;j<tweetWords.length;j++){
                if(tweetWords[j].toLowerCase().length() > 1){
                    words.add(tweetWords[j]);
                }
            }
            i++;
        }

        for (String word:words) {//implementacao de djb2
            int key = wordHash.hashingFunction(word);
            if(key<0)
                key*=-1;
            key = key%20000;
            while(true) {
                int collisions = 0;
                key = key%20000;
                if (wordHashTable[key].getWord() == "") {
                    wordHashTable[key].setWord(word);
                    wordHashTable[key].addRepetition();
                    break;
                } else {
                    if (wordHashTable[key].getWord().equals(word)) {
                        wordHashTable[key].addRepetition();
                        break;
                    } else {
                        collisions++;
                        key+=(collisions*collisions);
                    }
                }
            }
        }


        MergeSort sort = new MergeSort();
        sort.sort(wordHashTable);
        for(i=0;i<numWords;i++){
            System.out.println(wordHashTable[19133].getWord() + "\t" + wordHashTable[i].getRepetitions());
        }

    }

}

