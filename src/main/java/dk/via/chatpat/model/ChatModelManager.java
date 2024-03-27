package dk.via.chatpat.model;

import dk.via.chatpat.client.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;

public class ChatModelManager implements ChatModel, PropertyChangeListener {

    private final ChatClient client;
    private final PropertyChangeSupport support;
    private Chatter chatter;
    public ChatModelManager(ChatClient client) {
        this.client = client;
        this.client.addPropertyChangeListener(this);
        this.support = new PropertyChangeSupport(this);
    }

    public void setChatter(Chatter chatter) {
        System.out.println("Setting chatter to " + chatter);
        client.newChatter(chatter);
        this.chatter = chatter;
    }

    @Override
    public Chatter getChatter() {
        return chatter;
    }
    @Override
    public ArrayList<Chatter> getChatters() throws IOException {
        return this.client.getChatters();
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
