package dk.via.calculator.model;

public class Message {
    private String content;
    private User sender;
    private User receiver;
    public Message(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "From: " + sender + " To: " + receiver + " Content: " + content;
    }
}

