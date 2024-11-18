package br.team.xpulse.Model;

public class User {

    private String name;
    private String availability;
    private String type;

    public User(String name) {
        this.name = name;
        this.availability = "Available";
        type = "Guest";
    }

    public User(String name, String availability, String type) {
        this.name = name;
        this.availability = availability;
        this.type = type;
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
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
