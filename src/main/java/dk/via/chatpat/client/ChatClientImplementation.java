package dk.via.chatpat.client;

import dk.via.chatpat.model.*;
import dk.via.chatpat.server.StreamFactory;

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
    private final PropertyChangeSupport support;
    private final MessageListener listener;
    public ChatClientImplementation(String host, int port) throws IOException {
        socket = new Socket(host, port);
        output = StreamFactory.createWriter(socket);
        input = StreamFactory.createReader(socket);
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
        String strMsg = message.toString();
        output.println(strMsg);
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

    public void receiveBroadcast(String msg) {
        String strMsg = msg;
        System.out.println("Received: " + strMsg);
        support.firePropertyChange("message_received", null, strMsg);
    }
}
