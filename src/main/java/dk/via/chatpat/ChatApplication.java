package dk.via.chatpat;

import dk.via.chatpat.shared.IChat;
import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.ChatModelManager;
import dk.via.chatpat.view.ViewHandler;
import dk.via.chatpat.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099); // get the registry instead
        IChat chatClient = (IChat) registry.lookup("chat");
        ChatModel chatModel = new ChatModelManager(chatClient);
        ViewModelFactory viewModelFactory = new ViewModelFactory(chatModel);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}