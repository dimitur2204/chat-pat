package dk.via.calculator.view;

import dk.via.calculator.viewmodel.LoginViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

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
        chatButton.setOnAction(event -> loginViewModel.openChat());
    }

    public Region getRoot() {
        return root;
    }
    public void reset() {
    }
}
