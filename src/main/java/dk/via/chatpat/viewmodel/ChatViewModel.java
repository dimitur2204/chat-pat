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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ChatViewModel implements PropertyChangeListener {
    private final ChatModel model;
    private SimpleStringProperty message;
    private SimpleStringProperty chatArea;
    public ChatViewModel(ChatModel model) {
        this.model = model;
        this.message = new SimpleStringProperty();
        this.chatArea = new SimpleStringProperty();
        model.addPropertyChangeListener(this);
    }

    public SimpleStringProperty messageProperty() {
        return message;
    }
    public SimpleStringProperty chatAreaProperty() {
        return chatArea;
    }

    public void sendMessage() {
        model.sendMessage(new Message(this.message.getValue(), model.getChatter()));
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
            if (evt.getPropertyName().equals("message_received")) {
                if (evt.getNewValue() instanceof Message msg) {
                    Date date = new Date(msg.getTimestamp());
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String formattedDate = sdf.format(date);
                    if (chatArea.get() == null) {
                        chatArea.set(formattedDate + " - " + msg.getSender() + ": " + msg.getContent() + "\n");
                        return;
                    }
                    chatArea.set(chatArea.get() + formattedDate + " - " + msg.getSender() + ": " + msg.getContent() + "\n");
                }
            }
        });
    }
}
