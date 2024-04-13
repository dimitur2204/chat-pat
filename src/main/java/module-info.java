module dk.via {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires java.rmi;
    requires remoteobserver;

    opens dk.via.chatpat.view to javafx.fxml;
    opens dk.via.chatpat to javafx.fxml;

    opens dk.via.chatpat.model to com.google.gson;

    exports dk.via.chatpat;
    exports dk.via.chatpat.shared;
}