package dk.via.chatpat.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String content;
    private Chatter sender;
    private long timestamp;
    public Message(String content, Chatter sender) {
        this.content = content;
        this.sender = sender;
        this.timestamp = new Date().getTime();
    }

    public Message(String content, Chatter sender, long timestamp) {
        this.content = content;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public Chatter getSender() {
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

