package dk.via.chatpat.shared;

import dk.via.chatpat.model.Message;
import dk.via.chatpat.model.Chatter;
import dk.via.remote.observer.RemotePropertyChangeListener;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IChat extends Remote, Serializable {
    void sendMessage(Message message) throws RemoteException;
    ArrayList<Chatter> getChatters() throws RemoteException;
    void newChatter(Chatter chatter) throws RemoteException;
    void addPropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
    void removePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
}
