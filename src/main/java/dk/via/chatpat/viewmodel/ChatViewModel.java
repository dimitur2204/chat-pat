package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.Message;
import dk.via.chatpat.model.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class ChatViewModel implements PropertyChangeListener {
    private final ChatModel model;
    private SimpleStringProperty message;
    public ChatViewModel(ChatModel model) {
        this.model = model;
        this.message = new SimpleStringProperty();
        model.addPropertyChangeListener(this);
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }

    public void sendMessage() {
        model.sendMessage(new Message(this.message.toString(), model.getChatter()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Message result = (Message) evt.getNewValue();
            message.set(result.toString());
        });
    }
}
