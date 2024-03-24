package dk.via.chatpat;

import dk.via.chatpat.client.ChatClient;
import dk.via.chatpat.client.ChatClientImplementation;
import dk.via.chatpat.model.ChatModel;
import dk.via.chatpat.model.ChatModelManager;
import dk.via.chatpat.view.ViewHandler;
import dk.via.chatpat.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
public class ChatApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        ChatClient chatClient = new ChatClientImplementation("localhost", 8080);
        ChatModel chatModel = new ChatModelManager(chatClient);
        ViewModelFactory viewModelFactory = new ViewModelFactory(chatModel);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}