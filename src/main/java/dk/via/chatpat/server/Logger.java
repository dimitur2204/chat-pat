package dk.via.chatpat.server;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;

public class Logger {
    private static Logger instance;
    private final ArrayList<Destination> destination = new ArrayList<>();
    private Logger() {
    }
    public void info(String message) {
        Date date = new Date();
        String msg = "INFO: " + message + " " + date;
        if (this.destination.contains(Destination.CONSOLE)) {
            System.out.println(msg);
        }
        if (this.destination.contains(Destination.FILE)) {
            writeToFile(msg);
        }
    }
    public void debug(String message) {
        Date date = new Date();
        String msg = "\n" + "DEBUG: " + message + " " + date + "\n";
        if (this.destination.contains(Destination.CONSOLE)) {
            System.out.println(msg);
        }
        if (this.destination.contains(Destination.FILE)) {
            writeToFile(msg);
        }
    }
    public void error(String message) {
        Date date = new Date();
        String msg = "ERROR: " + message + " " + date;
        if (this.destination.contains(Destination.CONSOLE)) {
            System.out.println(msg);
        }
        if (this.destination.contains(Destination.FILE)) {
            writeToFile(msg);
        }
    }
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
            instance.destination.add(Destination.CONSOLE);
        }
        return instance;
    }

    public void addDestination(Destination destination) {
       this.destination.add(destination);
    }

    private void writeToFile(String message) {
        try {
            File file = new File("log.txt");
            FileWriter writer = new FileWriter(file, true);
            writer.write(message + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
