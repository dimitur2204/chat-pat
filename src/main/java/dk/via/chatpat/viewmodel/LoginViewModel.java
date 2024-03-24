package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;
import javafx.beans.property.SimpleStringProperty;

public class LoginViewModel {

    private SimpleStringProperty name;
    private ChatModel model;

    public LoginViewModel(ChatModel model) {
        name = new SimpleStringProperty();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }
    public void openChat() {
    }
}
