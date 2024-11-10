package br.team.xpulse.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import br.team.xpulse.Model.Room;
import br.team.xpulse.R;
import br.team.xpulse.Utils.RoomSection.RoomFragment;
import br.team.xpulse.Utils.RoomSection.RoomManager;

public class HomeScreen extends AppCompatActivity {

    private ImageButton btnAdd;
    private TextView lblTotal;

    private static final String TAG = "HomeScreen"; // Tag para logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, AddScreen.class);
            startActivity(intent);
        });

        lblTotal = findViewById(R.id.matches_count);

        // Recebe o Bundle enviado de AddScreen
        Bundle bundle = getIntent().getBundleExtra("roomBundle");
        if (bundle != null) {
            Room receivedRoom = (Room) bundle.getSerializable("newRoom");
            if (receivedRoom != null) {
                Log.d("HomeScreen", "Room Received: " + receivedRoom.toString());
                RoomManager.getInstance().addRoom(receivedRoom);
                updateRoomFragment();
            }
        }

        // Inicializa o fragmento com a lista do RoomManager
        updateRoomFragment();
    }

    private void updateRoomFragment() {
        RoomFragment roomFragment = RoomFragment.newInstance(RoomManager.getInstance().getRoomList());

        // Substituir o fragmento na transação
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_rooms, roomFragment);
        transaction.commit();

        // Verifique se há salas e atualize a visibilidade
        if (RoomManager.getInstance().getRoomList().isEmpty()) {
            findViewById(R.id.fragment_rooms).setVisibility(View.GONE);
            findViewById(R.id.image_empty).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.fragment_rooms).setVisibility(View.VISIBLE);
            findViewById(R.id.image_empty).setVisibility(View.GONE);

            lblTotal.setText("Total: " + RoomManager.getInstance().getRoomList().size());
        }
    }
}
