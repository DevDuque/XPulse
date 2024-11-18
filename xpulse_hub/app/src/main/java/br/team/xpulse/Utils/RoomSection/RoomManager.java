package br.team.xpulse.Utils.RoomSection;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import br.team.xpulse.Model.Room;

public class RoomManager {

    private static final String PREFS_NAME = "rooms_prefs";
    private static final String ROOMS_KEY = "rooms_key";
    private static RoomManager instance;
    private List<Room> roomList;
    private SharedPreferences sharedPreferences;

    private RoomManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loadRooms();
    }

    public static synchronized RoomManager getInstance(Context context) {
        if (instance == null) {
            instance = new RoomManager(context);
        }
        return instance;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void addRoom(Room room) {
        roomList.add(room);
        saveRooms();
    }

    private void saveRooms() {
        Gson gson = new Gson();
        String json = gson.toJson(roomList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ROOMS_KEY, json);
        editor.apply();
    }

    private void loadRooms() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ROOMS_KEY, "[]");
        Type type = new TypeToken<List<Room>>() {}.getType();
        roomList = gson.fromJson(json, type);
    }
}
