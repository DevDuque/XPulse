package br.team.xpulse.Model;

import java.io.Serializable;

public class Activity implements Serializable {

    private final String activityName;
    private final String type;
    private final Integer photoID;
    private final Integer maxPlayers;

    public String getActivityName() {
        return activityName;
    }

    public String getType() {
        return type;
    }

    public Integer getPhotoID() {
        return photoID;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Activity(String activityName, String type, Integer maxPlayers, Integer photoID) {
        this.maxPlayers = maxPlayers;
        this.photoID = photoID;
        this.type = type;
        this.activityName = activityName;
    }

    @Override
    public String toString() {
        return "Activity \n{" +
                "activityName='" + activityName + '\'' +
                ", type='" + type + '\'' +
                ", photoID=" + photoID +
                ", maxPlayers=" + maxPlayers +
                "} \n";
    }
}
