package dk.via.chatpat.model;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public interface ChatModel {
    User getChatter();
    void setChatter(User user);
    ArrayList<User> getChatters() throws IOException;
    void sendMessage(Message message);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
