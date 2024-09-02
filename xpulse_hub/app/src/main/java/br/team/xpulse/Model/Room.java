package br.team.xpulse.Model;

import java.util.Date;

public class Room {

    private String name;
    private String category;
    private String server;
    private Date dateTime;
    private Activity activity;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Activity getActivity() {
        return activity;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Room(String name, String category, String server, Activity activity, String description) {
        this.name = name;
        this.category = category;
        this.server = server;
        this.dateTime = new Date();
        this.activity = activity;
        this.description = description;
    }
}