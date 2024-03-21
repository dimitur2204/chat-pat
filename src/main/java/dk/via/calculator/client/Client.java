package dk.via.calculator.client;

import java.io.Closeable;

public interface Client extends Closeable {
    void receiveBroadcast(String message);
}
