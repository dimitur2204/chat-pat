package dk.via.chatpat.server;

import dk.via.chatpat.model.Chatter;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static ArrayList<Chatter> chatters = new ArrayList<>();
    public static void addChatter(Chatter chatter) {
        chatters.add(chatter);
    }
    public static ArrayList<Chatter> getChatters() {
        return chatters;
    }
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getInstance();
        logger.addDestination(Destination.FILE);
        ServerSocket serverSocket = new ServerSocket(8080);
        UDPBroadcaster broadcaster = new UDPBroadcaster("230.0.0.1", 8888);
        logger.info("Server started");
        while(true) {
            Socket socket = serverSocket.accept();
            logger.info("Client connected " + socket.getPort());
            ClientsListener runner = new ClientsListener(socket, broadcaster);
            Thread clientThread = new Thread(runner);
            clientThread.start();
        }
    }
}
