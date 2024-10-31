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

}
