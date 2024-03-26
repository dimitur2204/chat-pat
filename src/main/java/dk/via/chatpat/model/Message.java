package dk.via.chatpat.model;

import java.util.Date;

public class Message {
    private String content;
    private User sender;
    private long timestamp;
    public Message(String content, User sender) {
        this.content = content;
        this.sender = sender;
        this.timestamp = new Date().getTime();
    }

    public Message(String content, User sender, long timestamp) {
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public User getSender() {
        return sender;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String date = new Date(timestamp).toString();
        return "From: " + sender + content + " At: " + date;
    }
}

