package dk.via.chatpat.viewmodel;

import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.User;
import dk.via.chatpat.view.ViewFactory;
import dk.via.chatpat.view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;

public class LoginViewModel {

    private SimpleStringProperty name;
    private ChatModel model;

    public LoginViewModel(ChatModel model) {
        name = new SimpleStringProperty();
        this.model = model;
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void openChat(ViewHandler viewHandler) {
        viewHandler.openView(ViewFactory.CHAT);
        model.setChatter(new User(name.get()));
    }
}