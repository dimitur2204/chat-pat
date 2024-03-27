package dk.via.chatpat.client;

import dk.via.chatpat.model.Message;
import dk.via.chatpat.model.Chatter;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public interface ChatClient extends Client {
    void sendMessage(Message message);
    ArrayList<Chatter> getChatters() throws IOException;
    void newChatter(Chatter chatter);
    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
