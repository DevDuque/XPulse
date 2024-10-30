package br.team.xpulse.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Locale;

public class Room implements Serializable {

    private final String name;
    private final String server;
    private final Date dateTime;
    private final Activity activity;
    private final String description;
    private final List<String> tags;

    public Room(String name, String server, Date dateTime, Activity activity, String description,  List<String> tags) {
        this.name = name;
        this.server = server;
        this.dateTime = dateTime;
        this.activity = activity;
        this.description = description;
        this.tags = tags;
    }

    // Getters


    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        // Formata a data no formato desejado
        SimpleDateFormat formatter = new SimpleDateFormat("EEE - dd/MM - HH:mm", Locale.getDefault());
        String formattedDate = formatter.format(dateTime);

        return "Room \n {" +
                "name='" + name + '\'' +
                ", server='" + server + '\'' +
                ", dateTime=" + formattedDate +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                "\n" + activity.toString() +
                "} \n";
    }

}
