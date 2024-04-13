package dk.via.chatpat.view;

import dk.via.chatpat.viewmodel.LoginViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import java.rmi.RemoteException;

public class LoginViewController {
    public TextField nameField;
    public Button chatButton;
    public Region root;
    private ViewHandler viewHandler;
    private LoginViewModel viewModel;

    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = loginViewModel;
        this.root = root;
        nameField.textProperty().bindBidirectional(loginViewModel.nameProperty());
        chatButton.setOnAction(event -> {
            try {
                loginViewModel.openChat(viewHandler);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Region getRoot() {
        return root;
    }
    public void reset() {
    }
}
