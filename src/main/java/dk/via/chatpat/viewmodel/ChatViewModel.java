package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.Message;
import dk.via.chatpat.model.Chatter;
import dk.via.chatpat.shared.MessageType;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ChatViewModel implements PropertyChangeListener {
    private final ChatModel model;
    private SimpleStringProperty message;
    private SimpleStringProperty chatArea;
    private ObservableList<Chatter> chatters;

    public ChatViewModel(ChatModel model) throws IOException {
        this.model = model;
        this.message = new SimpleStringProperty();
        this.chatArea = new SimpleStringProperty();
        this.chatters = FXCollections.observableList(model.getChatters());
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

    public ObservableList<Chatter> getChatters() {
        return this.chatters;
    }

    private void addChatter(Chatter chatter) {
        this.chatters.add(chatter);
    }

    private void appendToChat(String message) {
        if (chatArea.get() == null) {
            chatArea.set(message);
            return;
        }
        chatArea.set(chatArea.get() + message);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            String propName = evt.getPropertyName();
            Object newValue = evt.getNewValue();
            if (propName.equals(MessageType.SEND_MESSAGE)) {
                if (newValue instanceof Message msg) {
                    Date date = new Date(msg.getTimestamp());
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                    String formattedDate = sdf.format(date);
                    appendToChat(formattedDate + " - " + msg.getSender() + ": " + msg.getContent() + "\n");
                }
            }
            if (propName.equals(MessageType.NEW_CHATTER)) {
                if (newValue instanceof Chatter chatter) {
                    addChatter(chatter);
                    appendToChat(chatter + " has just joined the chat." + "\n");
                }
            }
        });
    }
}
