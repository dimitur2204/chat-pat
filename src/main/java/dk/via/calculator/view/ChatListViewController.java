package dk.via.calculator.view;

import dk.via.calculator.model.User;
import dk.via.calculator.viewmodel.ChatListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class ChatListViewController {
    @FXML
    public ListView<User> senderList;
    @FXML
    public ListView<User> receiverList;
    private ViewHandler viewHandler;
    private ChatListViewModel viewModel;
    private Region root;
    public void init(ViewHandler viewHandler, ChatListViewModel chatListViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = chatListViewModel;
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
