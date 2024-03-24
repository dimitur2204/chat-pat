package dk.via.calculator.viewmodel;

import dk.via.calculator.model.ChatModel;
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
