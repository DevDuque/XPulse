package br.team.xpulse.Model;

public class User {

    private String name;
    private String availability;
    private String Type;

    public User(String name) {
        this.name = name;
        this.availability = "Available";
        Type = "Guest";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

}
