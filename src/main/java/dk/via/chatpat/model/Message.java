package dk.via.chatpat.model;

import java.util.Date;

public class Message {
    private String content;
    private User sender;
    private Date date;
    public Message(String content, User sender) {
        this.content = content;
        this.sender = sender;
        this.date = new Date();
    }
    @Override
    public String toString() {
        return "From: " + sender + content + " At: " + date;
    }
}

