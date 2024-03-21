package dk.via.calculator.model;

import java.beans.PropertyChangeListener;

public interface ChatModel {

    void sendMessage(Message message);
    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
