package dk.via.calculator;

import dk.via.calculator.client.ChatClient;
import dk.via.calculator.client.ChatClientImplementation;
import dk.via.calculator.model.ChatModel;
import dk.via.calculator.model.ChatModelManager;
import dk.via.calculator.view.ViewHandler;
import dk.via.calculator.viewmodel.ViewModelFactory;
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