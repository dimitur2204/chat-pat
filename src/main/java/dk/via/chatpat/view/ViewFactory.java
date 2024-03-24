package dk.via.chatpat.view;

import dk.via.chatpat.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {
    public static final String LOGIN = "login";
    public static final String CHAT = "chat";

    private final ViewHandler viewHandler;
    private final ViewModelFactory viewModelFactory;
    private ChatViewController chatViewController;
    private LoginViewController loginViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.chatViewController = null;
        this.loginViewController = null;
    }

    public Region loadLoginView() {
        if (loginViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginView.fxml"));
            try {
                Region root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(viewHandler, viewModelFactory.getLoginViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        loginViewController.reset();
        return loginViewController.getRoot();
    }
    public Region loadChatView() {
        if (chatViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatView.fxml"));
            try {
                Region root = loader.load();
                chatViewController = loader.getController();
                chatViewController.init(viewHandler, viewModelFactory.getChatViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        chatViewController.reset();
        return chatViewController.getRoot();
    }

    public Region load(String id) {
        Region root = switch(id) {
            case LOGIN -> loadLoginView();
            case CHAT -> loadChatView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
        return root;
    }
}
