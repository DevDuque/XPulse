package br.team.xpulse.Model;

public class Room {

    private String name;
    private String activity;
    private Integer maxPlayers;
    private String description;

    public Room(String name, String activity, Integer maxPlayers, String description) {
        this.name = name;
        this.activity = activity;
        this.maxPlayers = maxPlayers;
        this.description = description;
    }
}
