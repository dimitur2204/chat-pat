package dk.via.chatpat.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private ArrayList<Message> messages;
    private ArrayList<User> chatters;

    public Chat() {
        this.messages = new ArrayList<>();
        this.chatters = new ArrayList<>();
    }

    public void addUser(User user) {
        chatters.add(user);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
}
