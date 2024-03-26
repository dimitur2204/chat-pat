package dk.via.chatpat.client;

import dk.via.chatpat.model.*;
import dk.via.chatpat.server.StreamFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClientImplementation implements ChatClient {

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

        listener = new MessageListener(this, "230.0.0.1", 8888);
        Thread thread = new Thread(listener);
        thread.start();
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing connection...");
        output.println(MessageType.EXIT);
        output.flush();
        socket.close();
        listener.close();
    }

    @Override
    public void sendMessage(Message message) {
        output.println(MessageType.SEND_MESSAGE + " " + message.getSender() + " " +  message.getTimestamp() + " " + message.getContent());
        output.flush();
    }

    public ArrayList<User> getChatters() throws IOException {
        output.println(MessageType.GET_CHATTERS);
        output.flush();
        ArrayList<User> chatters = new ArrayList<>();
        while (true) {
            String chatter = input.readLine();
            if (chatter.equals("END")) break;
            chatters.add(new User(chatter));
        }
        return chatters;
    }

    @Override
    public void newChatter(User user) {
        output.println(MessageType.NEW_CHATTER + " " + user);
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
        String[] parts = msg.split(" ");
        String cmd = parts[0];
        switch (cmd) {
            case MessageType.SEND_MESSAGE -> {
                String sender = parts[1];
                String timestamp = parts[2];
                StringBuilder message = new StringBuilder();
                for (int i = 0; i < parts.length; i++) {
                   if (i > 2) message.append(parts[i]).append(" ");
                }
                support.firePropertyChange("message_received", null, new Message(message.toString(), new User(sender), Long.parseLong(timestamp)));
            }
            case MessageType.NEW_CHATTER -> {
                support.firePropertyChange("new_chatter", null, new User(parts[1]));
            }
        }
    }
}
