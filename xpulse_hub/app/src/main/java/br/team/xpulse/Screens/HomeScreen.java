package br.team.xpulse.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

import br.team.xpulse.Model.Room;
import br.team.xpulse.R;
import br.team.xpulse.Utils.RoomSection.RoomFragment;

public class HomeScreen extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private List<Room> roomList = new ArrayList<>(); // Lista para armazenar as salas

    ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_screen);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, AddScreen.class);
            startActivity(intent);
        });

        // Recebe o Bundle enviado de AddScreen
        Bundle bundle = getIntent().getBundleExtra("roomBundle");
        if (bundle != null) {
            Room receivedRoom = (Room) bundle.getSerializable("newRoom");
            if (receivedRoom != null) {
                Log.d("HomeScreen", "Room Received: " + receivedRoom.toString());
                addRoomToList(receivedRoom);
                updateRoomFragment();
            }
        }

        // Inicializa o fragmento com a lista vazia ao iniciar
        updateRoomFragment();
    }

    private void addRoomToList(Room room) {
        roomList.add(room);
    }

    private void updateRoomFragment() {
        RoomFragment roomFragment = RoomFragment.newInstance(roomList);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_rooms, roomFragment);
        transaction.commit();

        // Verifica se a lista de salas está vazia
        if (roomList.isEmpty()) {
            findViewById(R.id.fragment_rooms).setVisibility(View.GONE);  // Esconde o fragmento
            findViewById(R.id.image_empty).setVisibility(View.VISIBLE); // Mostra a mensagem de vazio
        } else {
            findViewById(R.id.fragment_rooms).setVisibility(View.VISIBLE); // Mostra o fragmento
            findViewById(R.id.image_empty).setVisibility(View.GONE);      // Esconde a mensagem de vazio
        }
    }
}
