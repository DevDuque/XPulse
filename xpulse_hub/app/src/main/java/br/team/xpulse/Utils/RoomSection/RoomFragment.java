package br.team.xpulse.Utils.RoomSection;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

import br.team.xpulse.Model.Room;
import br.team.xpulse.R;
public class RoomFragment extends Fragment implements RoomAdapter.OnRoomClickListener {

    private List<Room> roomList; // Lista de salas
    private RoomAdapter roomAdapter; // Adicione esta linha

    public RoomFragment() {
    }

    public static RoomFragment newInstance(List<Room> rooms) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putSerializable("roomList", (Serializable) rooms);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            roomList = (List<Room>) getArguments().getSerializable("roomList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRooms);

        roomAdapter = new RoomAdapter(roomList, this); // Armazene o adapter aqui
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(roomAdapter);

        return view;
    }

    public RoomAdapter getAdapter() { // Adicione este método
        return roomAdapter;
    }

    @Override
    public void onRoomClick(Room room) {
    }
}
