package dk.via.chatpat.client;

import dk.via.chatpat.model.Chat;
import dk.via.chatpat.model.Message;

import java.beans.PropertyChangeListener;

public interface ChatClient extends Client {
    void sendMessage(Message message);

    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);

    Chat connectOrCreateChat();
}
