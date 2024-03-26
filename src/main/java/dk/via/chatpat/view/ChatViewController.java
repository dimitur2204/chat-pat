package dk.via.chatpat.view;

import dk.via.chatpat.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class ChatViewController {
    @FXML
    public TextArea chatArea;

    @FXML
    public TextField msgField;
    @FXML
    public Button sendBtn;
    private ViewHandler viewHandler;
    private ChatViewModel viewModel;
    private Region root;
    public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = chatViewModel;
        msgField.textProperty().bindBidirectional(chatViewModel.messageProperty());
        sendBtn.setOnAction(event -> chatViewModel.sendMessage());
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
