package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.Message;
import dk.via.chatpat.model.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

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

    public ObservableList<User> getChatters() {
        try {
            ArrayList<User> chatters = model.getChatters();
            return FXCollections.observableList(chatters);
        } catch (Exception e) {
            throw new RuntimeException("Server communication error", e);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Message result = (Message) evt.getNewValue();
            message.set(result.toString());
        });
    }
}
