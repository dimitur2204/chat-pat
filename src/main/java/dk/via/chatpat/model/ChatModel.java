package dk.via.chatpat.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatModel {
    Chatter getChatter();
    void setChatter(Chatter chatter) throws RemoteException;
    ArrayList<Chatter> getChatters() throws IOException;
    void sendMessage(Message message);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
