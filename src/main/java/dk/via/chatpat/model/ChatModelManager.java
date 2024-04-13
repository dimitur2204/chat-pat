package dk.via.chatpat.model;

import dk.via.chatpat.shared.IChat;
import dk.via.chatpat.shared.MessageType;
import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatModelManager extends UnicastRemoteObject implements ChatModel, RemotePropertyChangeListener<IChat> {

    private final IChat client;
    private final PropertyChangeSupport support;
    private Chatter chatter;

    public ChatModelManager(IChat client) throws RemoteException {
        super();
        this.client = client;
        this.client.addPropertyChangeListener(this);
        this.support = new PropertyChangeSupport(this);
    }

    public void setChatter(Chatter chatter) throws RemoteException {
        System.out.println("Setting chatter to " + chatter);
        client.newChatter(chatter);
        this.chatter = chatter;
    }

    @Override
    public Chatter getChatter() {
        return chatter;
    }

    @Override
    public ArrayList<Chatter> getChatters() {
        try {
            return this.client.getChatters();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(Message message) {
        try {
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
    public void propertyChange(RemotePropertyChangeEvent evt) {
        System.out.println("Property change: " + evt.getPropertyName());
        support.firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }
}
