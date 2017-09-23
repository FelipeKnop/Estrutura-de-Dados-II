package models;

public class WordFrequency implements Comparable<WordFrequency>{

    private String word;
    private int count;

    public WordFrequency(String word) {
        this.word = word;
        this.count = 1;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    @Override
    public int compareTo(WordFrequency o) {
        return Integer.compare(o.getCount(), this.getCount());
    }
}

