package dk.via.chatpat.server;

import dk.via.chatpat.client.MessageType;
import dk.via.chatpat.model.Chatter;

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
                if(msg == null) {
                    output.println("Terminate connection");
                    Logger.getInstance().info("Client disconnected: " + socket.getPort());
                    return;
                }
                String cmd = msg.split(" ")[0];
                switch (cmd) {
                    case MessageType.EXIT:
                        output.println("Terminate connection");
                        Logger.getInstance().info("Client disconnected: " + socket.getPort());
                        return;
                    case MessageType.NEW_CHATTER:
                        Chatter newChatter = new Chatter(msg.split(" ")[1]);
                        //Check if the chatter is already in the list and change to online if it is
                        for (Chatter chatter : ChatServer.getChatters()) {
                            if (chatter.equals(newChatter)) {
                                chatter.setOnline(true);
                                broadcaster.broadcast("NEW_CHATTER " + chatter);
                                Logger.getInstance().info("Chatter " + chatter + " is online" + socket.getPort());
                                return;
                            }
                        }
                        newChatter.setOnline(true);
                        ChatServer.addChatter(newChatter);
                        Logger.getInstance().info("New chatter: " + newChatter + " from " + socket.getPort());
                        broadcaster.broadcast("NEW_CHATTER " + newChatter);
                        break;
                    case MessageType.GET_CHATTERS:
                        ArrayList<Chatter> chatters = ChatServer.getChatters();
                        for (Chatter chatter : chatters) {
                            output.println(chatter.toString());
                        }
                        Logger.getInstance().info("Sent chatters to client" + socket.getPort());
                        output.println("END");
                        break;
                    case MessageType.SEND_MESSAGE:
                        // Format: SEND_MESSAGE <message> <sender> <timestamp>
                        broadcaster.broadcast(msg);
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
