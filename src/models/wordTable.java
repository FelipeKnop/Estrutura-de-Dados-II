package models;

public class wordTable implements Comparable<wordTable>{
    private String word = "";
    private int repetitions = 0;

    public String getWord(){
        return this.word;
    }

    public int getRepetitions(){
        return this.repetitions;
    }

    public void addRepetition(){
        this.repetitions++;
    }

    public void setWord(String word){
        this.word = word;
    }

    @Override
    public int compareTo(wordTable o) {
        return -1*Integer.compare(this.repetitions, o.getRepetitions());
    }
}

