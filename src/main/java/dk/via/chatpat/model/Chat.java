package dk.via.chatpat.model;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private ArrayList<Message> messages;
    private ArrayList<Chatter> chatters;

    public Chat() {
        this.messages = new ArrayList<>();
        this.chatters = new ArrayList<>();
    }

    public void addUser(Chatter chatter) {
        chatters.add(chatter);
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }
}
