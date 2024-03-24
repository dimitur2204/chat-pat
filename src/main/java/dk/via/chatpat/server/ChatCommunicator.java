package dk.via.chatpat.server;

import com.google.gson.Gson;
import dk.via.chatpat.model.*;
import dk.via.chatpat.socket.StreamFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatCommunicator implements Runnable {
    private final Socket socket;
    private final UDPBroadcaster broadcaster;
    private final Gson gson;

    public ChatCommunicator(Socket socket, UDPBroadcaster broadcaster) {
        this.socket = socket;
        this.broadcaster = broadcaster;
        this.gson = new Gson();
    }

    private void communicate() throws IOException {
        try {
            BufferedReader input = StreamFactory.createReader(socket);
            PrintWriter output = StreamFactory.createWriter(socket);
            while (true) {
                String json = input.readLine();
                Message message = gson.fromJson(json, Message.class);
                output.flush();
                broadcaster.broadcast(message.toString());
            }
        } finally {
            socket.close();
        }
    }

    @Override
    public void run() {
        try {
            communicate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
