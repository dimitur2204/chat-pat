package dk.via.calculator.model;

import dk.via.calculator.client.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel, PropertyChangeListener {
    private final ChatClient client;
    private final PropertyChangeSupport support;
    public ChatModelManager(ChatClient client) {
        this.client = client;
        this.support = new PropertyChangeSupport(this);
    }
    @Override
    public void sendMessage(Message message) {
        try{
            client.sendMessage(message);
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
