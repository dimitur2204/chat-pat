package dk.via.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private ArrayList<Message> messages;
    private User user1;
    private User user2;
    public Chat(List<Message> messages, User user1, User user2) {
        this.messages = new ArrayList<>(messages);
        this.user1 = user1;
        this.user2 = user2;
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
}
