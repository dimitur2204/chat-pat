package dk.via.calculator.client;

import dk.via.calculator.model.Chat;
import dk.via.calculator.model.Message;
import dk.via.calculator.model.User;

import java.beans.PropertyChangeListener;

public interface ChatClient extends Client {
    void sendMessage(Message message);

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    Chat connectOrCreateChat();
}
