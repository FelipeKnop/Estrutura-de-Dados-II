package models;

public class Tweet implements Comparable<Tweet> {

    private final Long userId;
    private final Long tweetId;
    private final String tweetText;
    private final String date;

    public Tweet(Long userId, Long tweetId, String tweetText, String date) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.tweetText = tweetText;
        this.date = date;
    }

    public Tweet(int userId, int tweetId, String tweetText, String date) {
        this.userId = (long) userId;
        this.tweetId = (long) tweetId;
        this.tweetText = tweetText;
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public String getTweetText() {
        return tweetText;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int compareTo(Tweet o) {
        return Math.toIntExact(this.tweetId - o.getTweetId());
    }
}
