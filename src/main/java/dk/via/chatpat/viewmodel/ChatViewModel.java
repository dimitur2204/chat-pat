package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.Message;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class ChatViewModel implements PropertyChangeListener {
    private final ChatModel model;
    private SimpleStringProperty message;
    public ChatViewModel(ChatModel model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Message result = (Message) evt.getNewValue();
            message.set(result.toString());
        });
    }
}
