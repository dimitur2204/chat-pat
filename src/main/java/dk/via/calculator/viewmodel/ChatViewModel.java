package dk.via.calculator.viewmodel;

import dk.via.calculator.model.Model;
import dk.via.calculator.model.Result;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class ChatViewModel implements PropertyChangeListener {
    private final Model model;
    private SimpleStringProperty message;
    public ChatViewModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Result result = (Result) evt.getNewValue();
            message.set(result.toString());
        });
    }
}
