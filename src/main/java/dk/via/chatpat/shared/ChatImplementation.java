package dk.via.chatpat.shared;

import dk.via.chatpat.model.*;
import dk.via.chatpat.server.Logger;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatImplementation implements IChat {

    private final RemotePropertyChangeSupport support;
    private ArrayList<Chatter> chatters;

    public ChatImplementation() {
        this.chatters = new ArrayList<>();
        support = new RemotePropertyChangeSupport<>();
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        Logger.getInstance().info("Broadcasted message: " + message);
        support.firePropertyChange(MessageType.SEND_MESSAGE, null, message);
    }

    public ArrayList<Chatter> getChatters() throws RemoteException {
        Logger.getInstance().info("Sent chatters to client");
        return this.chatters;
    }

    @Override
    public void newChatter(Chatter newChatter) throws RemoteException {
        for (Chatter chatter : this.chatters) {
            if (chatter.equals(newChatter)) {
                chatter.setOnline(true);
                Logger.getInstance().info("Chatter " + chatter + " is online");
                return;
            }
        }
        this.chatters.add(newChatter);
        Logger.getInstance().info("New chatter: " + newChatter);
        support.firePropertyChange(MessageType.NEW_CHATTER, null, newChatter);
    }

    @Override
    public void addPropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException {
        support.removePropertyChangeListener(listener);
    }
}
