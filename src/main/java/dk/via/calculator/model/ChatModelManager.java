package dk.via.calculator.model;

import dk.via.calculator.client.ChatClient;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatModelManager implements ChatModel, PropertyChangeListener {
    private final ChatClient client;
    private final ArrayList<User> chatters;
    private final PropertyChangeSupport support;
    private Chat currentChat;
    public ChatModelManager(ChatClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);

        this.chatters = new ArrayList<>();

        this.currentChat = null;

        // Test users
        User user1 = new User("Ivan");
        User user2 = new User("Plamen");
        User user3 = new User("Dimitar");
        this.addUser(user1);
        this.addUser(user2);
        this.addUser(user3);
    }
    @Override
    public void sendMessage(Message message) {
        try{
            client.sendMessage(message);
        } catch (Exception e) {
            throw new RuntimeException("Server communication error", e);
        }
    }

    public void addUser(User user) {
        chatters.add(user);
        support.firePropertyChange("NEW_CHATTER", null, user);
    }

    public void connectOrCreateChat() {
        try {
            currentChat = client.connectOrCreateChat();
        } catch (Exception e) {
            throw new RuntimeException("Server communication error", e);
        }
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
    }
}
