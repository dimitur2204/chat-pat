package dk.via.chatpat.view;

import dk.via.chatpat.model.Chatter;
import dk.via.chatpat.viewmodel.ChatViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    @FXML
    public ListView<Chatter> chattersList;
    private ViewHandler viewHandler;
    private ChatViewModel viewModel;
    private Region root;
    public void init(ViewHandler viewHandler, ChatViewModel chatViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = chatViewModel;
        msgField.textProperty().bindBidirectional(chatViewModel.messageProperty());
        chatArea.textProperty().bindBidirectional(chatViewModel.chatAreaProperty());
        sendBtn.setOnAction(event -> {
            chatViewModel.sendMessage();
            msgField.clear();
            msgField.requestFocus();
        });
        ObservableList<Chatter> chatters = chatViewModel.getChatters();
        chattersList.setItems(chatters);
        this.root = root;
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
    }
}
