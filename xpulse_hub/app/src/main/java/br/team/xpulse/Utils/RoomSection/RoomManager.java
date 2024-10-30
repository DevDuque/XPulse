package br.team.xpulse.Utils.RoomSection;

import java.util.ArrayList;
import java.util.List;

import br.team.xpulse.Model.Room;

public class RoomManager {
    private static RoomManager instance;
    private List<Room> roomList;

    private RoomManager() {
        roomList = new ArrayList<>();
    }

    public static synchronized RoomManager getInstance() {
        if (instance == null) {
            instance = new RoomManager();
        }
        return instance;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void addRoom(Room room) {
        roomList.add(room);
    }
}
