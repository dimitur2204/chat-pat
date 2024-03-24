package dk.via.chatpat.model;

public class User {
    private String name;
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User other = (User) obj;
            return name.equals(other.name);
        } else {
            return false;
        }
    }
}
