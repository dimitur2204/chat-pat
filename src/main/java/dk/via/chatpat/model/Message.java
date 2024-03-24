package dk.via.chatpat.model;

import java.util.Date;

public class Message {
    private String content;
    private User sender;
    private User receiver;
    private Date date;
    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.date = new Date();
    }
    @Override
    public String toString() {
        return "From: " + sender + " To: " + receiver + " Content: " + content + " At: " + date;
    }
}

