package dk.via.chatpat.model;

public class Chatter {
    private String name;
    private boolean online;
    public Chatter(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Chatter) {
            Chatter other = (Chatter) obj;
            return name.equals(other.name);
        } else {
            return false;
        }
    }
}
