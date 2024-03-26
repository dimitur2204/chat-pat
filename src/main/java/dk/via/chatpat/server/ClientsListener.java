package dk.via.chatpat.server;

import dk.via.chatpat.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientsListener implements Runnable {
    private final Socket socket;
    private final UDPBroadcaster broadcaster;

    public ClientsListener(Socket socket, UDPBroadcaster broadcaster){
        this.socket = socket;
        this.broadcaster = broadcaster;
    }

    private void listen() throws IOException {
        try {
            BufferedReader input = StreamFactory.createReader(socket);
            PrintWriter output = StreamFactory.createWriter(socket);
            while (true) {
                String msg = input.readLine();
                Logger.getInstance().info("Received: " + msg);
                String cmd = msg.split(" ")[0];
                switch (cmd) {
                    case "EXIT":
                        output.println("Terminate connection");
                        Logger.getInstance().info("Client disconnected: " + socket.getPort());
                        return;
                    case "NEW_CHATTER":
                        User newChatter = new User(msg.split(" ")[1]);
                        ChatServer.addChatter(newChatter);
                        Logger.getInstance().info("New chatter: " + newChatter + " from " + socket.getPort());
                        broadcaster.broadcast("NEW_CHATTER " + newChatter);
                        break;
                    case "GET_CHATTERS":
                        ArrayList<User> chatters = ChatServer.getChatters();
                        for (User chatter : chatters) {
                            output.println(chatter.toString());
                        }
                        Logger.getInstance().info("Sent chatters to client" + socket.getPort());
                        output.println("END");
                        break;
                    case "SEND_MESSAGE":
                        // Format: SEND_MESSAGE <message> <sender> <timestamp>
                        broadcaster.broadcast("NEW_MESSAGE " + msg);
                        Logger.getInstance().info("Broadcasted message: " + msg + " from " + socket.getPort());
                        break;
                }
                output.flush();
            }
        } finally {
            socket.close();
        }
    }

    @Override
    public void run() {
        try {
            listen();
        } catch (Exception e) {
            Logger.getInstance().error("Error in client listener " + e);
            e.printStackTrace();
        }
    }
}
