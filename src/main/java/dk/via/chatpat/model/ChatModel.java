package dk.via.chatpat.model;

import java.beans.PropertyChangeListener;

public interface ChatModel {
    User getChatter();
    void setChatter(User user);
    void sendMessage(Message message);
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
}
