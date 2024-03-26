package dk.via.chatpat.client;

import dk.via.chatpat.model.Chat;
import dk.via.chatpat.model.Message;
import dk.via.chatpat.model.User;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public interface ChatClient extends Client {
    void sendMessage(Message message);
    ArrayList<User> getChatters() throws IOException;
    void newChatter(User user);
    void addPropertyChangeListener(PropertyChangeListener listener);

    void removePropertyChangeListener(PropertyChangeListener listener);
}
