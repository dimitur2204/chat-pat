package dk.via.chatpat.client;

import java.io.IOException;
import java.net.*;
import java.nio.channels.AsynchronousCloseException;

public class MessageListener implements Runnable {
    private final Client client;
    private final MulticastSocket multicastSocket;
    private final InetSocketAddress socketAddress;
    private final NetworkInterface netInterface;

    public MessageListener(Client client, String groupAddress, int port) throws IOException {
        this.client = client;
        this.multicastSocket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName(groupAddress);
        this.socketAddress = new InetSocketAddress(group, port);
        this.netInterface = NetworkInterface.getByInetAddress(group);
    }

    public void run() {
        try {
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String lastPacket = null;

    private void listen() throws IOException {
        multicastSocket.joinGroup(socketAddress, netInterface);
        byte[] content = new byte[32768];
        while (true) {
            DatagramPacket packet = new DatagramPacket(content, content.length);
            multicastSocket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength());
            if(message.equals(lastPacket)) continue;
            lastPacket = message;
            client.receiveBroadcast(message);
        }
    }

    public void close() throws IOException {
        multicastSocket.leaveGroup(socketAddress, netInterface);
        multicastSocket.close();
    }
}
