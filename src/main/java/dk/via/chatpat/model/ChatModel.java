package dk.via.chatpat.model;

import java.beans.PropertyChangeListener;

public interface ChatModel {

    void sendMessage(Message message);
    void addUser(User user);
    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
