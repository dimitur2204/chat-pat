package dk.via.chatpat.client;

import java.io.Closeable;

public interface Client extends Closeable {
    void receiveBroadcast(String message);
}
