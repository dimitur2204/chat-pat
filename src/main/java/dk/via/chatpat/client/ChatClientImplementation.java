package dk.via.chatpat.client;

import com.google.gson.Gson;
import dk.via.chatpat.model.*;
import dk.via.chatpat.socket.StreamFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientImplementation implements ChatClient {
    private static final String EXIT_JSON = """
    {"operator": "exit"}
    """;

    private final Socket socket;
    private final PrintWriter output;
    private final BufferedReader input;
    private final Gson gson;
    private final PropertyChangeSupport support;
    private final MessageListener listener;
    public ChatClientImplementation(String host, int port) throws IOException {
        socket = new Socket(host, port);
        output = StreamFactory.createWriter(socket);
        input = StreamFactory.createReader(socket);
        gson = new Gson();
        support = new PropertyChangeSupport(this);

        listener = new MessageListener(this, "230.0.0.0", 8888);
        Thread thread = new Thread(listener);
        thread.start();
    }

    @Override
    public void close() throws IOException {
        output.println(EXIT_JSON);
        output.flush();
        socket.close();
        listener.close();
    }

    @Override
    public void sendMessage(Message message) {
        String jsonMsg = gson.toJson(message);
        output.println(jsonMsg);
        output.flush();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public Chat connectOrCreateChat() {
        Chat chat = new Chat();
        return chat;
    }

    public void receiveBroadcast(String jsonMsg) {
        Message message = gson.fromJson(jsonMsg, Message.class);
        support.firePropertyChange("message_received", null, message);
    }
}
