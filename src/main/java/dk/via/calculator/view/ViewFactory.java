package dk.via.calculator.view;

import dk.via.calculator.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.io.IOError;
import java.io.IOException;

public class ViewFactory {
    public static final String CALCULATOR = "calculator";
    public static final String CHAT_LIST = "chat_list";

    private final ViewHandler viewHandler;
    private final ViewModelFactory viewModelFactory;
    private CalculateViewController convertViewController;
    private ChatListViewController chatListViewController;

    public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
        this.viewHandler = viewHandler;
        this.viewModelFactory = viewModelFactory;
        this.convertViewController = null;
        this.chatListViewController = null;
    }

    public Region loadConvertView() {
        if (convertViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CalculatorView.fxml"));
            try {
                Region root = loader.load();
                convertViewController = loader.getController();
                convertViewController.init(viewHandler, viewModelFactory.getConvertViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        convertViewController.reset();
        return convertViewController.getRoot();
    }
    public Region loadChatListView() {
        if (chatListViewController == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ChatListView.fxml"));
            try {
                Region root = loader.load();
                chatListViewController = loader.getController();
                chatListViewController.init(viewHandler, viewModelFactory.getChatListViewModel(), root);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        chatListViewController.reset();
        return chatListViewController.getRoot();
    }

    public Region load(String id) {
        Region root = switch(id) {
            case CALCULATOR -> loadConvertView();
            case CHAT_LIST -> loadChatListView();
            default -> throw new IllegalArgumentException("Unknown view: " + id);
        };
        return root;
    }
}
