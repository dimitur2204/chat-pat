package dk.via.chatpat.server;

import dk.via.chatpat.shared.ChatImplementation;
import dk.via.chatpat.shared.IChat;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(1099);
        IChat runner = new ChatImplementation();
        Remote remote = UnicastRemoteObject.exportObject(runner, 8888);
        registry.bind("chat", remote);
        Logger logger = Logger.getInstance();
        logger.addDestination(Destination.FILE);
        logger.info("Server started");
    }
}
