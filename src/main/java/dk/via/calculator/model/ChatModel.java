package dk.via.calculator.model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface ChatModel {

    void sendMessage(Message message);
    void addUser(User user);
    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
