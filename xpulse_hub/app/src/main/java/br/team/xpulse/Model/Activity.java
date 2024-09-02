package br.team.xpulse.Model;

public class Activity {

    private String activityName;
    private String type;
    private Integer photoID;
    private Integer maxPlayers;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Integer photoID) {
        this.photoID = photoID;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Activity(String activityName, String type, Integer maxPlayers, Integer photoID) {
        this.maxPlayers = maxPlayers;
        this.photoID = photoID;
        this.type = type;
        this.activityName = activityName;
    }
}
