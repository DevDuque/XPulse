package br.team.xpulse.Utils.RoomSection;

import android.content.Intent;
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
import br.team.xpulse.Screens.RoomScreen;

public class RoomFragment extends Fragment implements RoomAdapter.OnRoomClickListener {

    private List<Room> roomList; // Lista de salas
    private RoomAdapter roomAdapter; // Adapter da lista de salas

    public RoomFragment() {
    }

    // Método para criar uma nova instância do fragmento, passando a lista de salas
    public static RoomFragment newInstance(List<Room> rooms) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putSerializable("roomList", (Serializable) rooms); // Passando a lista de salas para o fragmento
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Verificando se há dados passados no Bundle e recuperando a lista de salas
        if (getArguments() != null) {
            roomList = (List<Room>) getArguments().getSerializable("roomList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_room_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRooms);

        // Inicializando o adapter e passando a lista de salas
        roomAdapter = new RoomAdapter(roomList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(roomAdapter);

        return view;
    }

    @Override
    public void onRoomClick(Room room) {
        // Quando um item da lista for clicado, passa a sala via Intent para a RoomScreen
        Intent intent = new Intent(getContext(), RoomScreen.class);

        // Adiciona a sala no Bundle do Intent
        Bundle bundle = new Bundle();
        bundle.putSerializable("room", room);  // Passando a sala como serializável
        intent.putExtras(bundle); // Adicionando o Bundle no Intent

        // Inicia a RoomScreen
        startActivity(intent);
    }
}
