package br.team.xpulse.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Date;
import java.util.Locale;

public class Room {

    private String name;
    private String category;
    private String server;
    private Date dateTime;
    private Activity activity;
    private String description;

    public Room(String name, String category, String server, Date dateTime, Activity activity, String description) {
        this.name = name;
        this.category = category;
        this.server = server;
        this.dateTime = dateTime;
        this.activity = activity;
        this.description = description;
    }

    // Getters e setters...

    @Override
    public String toString() {
        // Formata a data no formato desejado
        SimpleDateFormat formatter = new SimpleDateFormat("EEE - dd/MM - HH:mm", Locale.getDefault());
        String formattedDate = formatter.format(dateTime);

        return "Room{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", server='" + server + '\'' +
                ", dateTime=" + formattedDate +
                ", activity=" + activity.toString() +
                ", description='" + description + '\'' +
                '}';
    }
}
