package br.team.xpulse.Screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.team.xpulse.Model.Room;
import br.team.xpulse.R;
import br.team.xpulse.Utils.UserSection.PlayersFragment;

public class RoomScreen extends AppCompatActivity {

    private TextView lblTitle, lblDescription, lblName;
    private ImageButton btnShare, btnBack;
    private Button btnDisc;
    private ImageView roomImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_screen);

        // Inicializando os componentes de UI
        lblName = findViewById(R.id.lbl_name);
        lblTitle = findViewById(R.id.lbl_title);
        lblDescription = findViewById(R.id.lbl_description);

        btnBack = findViewById(R.id.btn_back);
        btnShare = findViewById(R.id.btn_share);
        btnDisc = findViewById(R.id.btn_disc);

        roomImage = findViewById(R.id.room_image);

        // Recupera o Room enviado via Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Room room = (Room) bundle.getSerializable("room");  // Recuperando a sala

            if (room != null) {
                // Atualizando a UI com as informações da sala
                lblTitle.setText(room.getName());
                lblDescription.setText(room.getDescription());
                lblName.setText(room.getActivity().getActivityName());

                // Definindo a imagem baseada no nome da atividade
                setActivityImage(room.getActivity().getActivityName());

                // Configurando o botão de compartilhar
                btnShare.setOnClickListener(v -> shareRoom(room));

                btnBack.setOnClickListener(v -> finish());

                btnDisc.setOnClickListener(v -> openServer(room));
            }
        }

        // Adicionando o PlayersFragment dinamicamente ao FrameLayout
        addPlayersFragment();
    }

    // Método para definir a imagem da atividade com base no nome
    private void setActivityImage(String activityName) {
        switch (activityName) {
            case "League of Legends":
                roomImage.setImageResource(R.drawable.img_league);
                break;
            case "Valorant":
                roomImage.setImageResource(R.drawable.img_valorant);
                break;
            case "Warzone":
                roomImage.setImageResource(R.drawable.img_warzone);
                break;
            case "Minecraft":
                roomImage.setImageResource(R.drawable.img_minecraft);
                break;
            case "Conversa":
                roomImage.setImageResource(R.drawable.img_empty);
                break;
            default:
                roomImage.setImageResource(R.drawable.ic_calendar);
                break;
        }
    }

    // Método para compartilhar as informações da sala
    private void shareRoom(Room room) {
        String roomInfo = "Room Info:\n" +
                "Name: " + room.getName() + "\n" +
                "Activity: " + room.getActivity().getActivityName() + "\n" +
                "Description: " + room.getDescription() + "\n" +
                "Tags: " + String.join(", ", room.getTags()) + "\n";

        Date date = room.getDateTime();
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
            String formattedDate = dateFormat.format(date);
            roomInfo += "Date: " + formattedDate;
        }

        String shareText = "XPulse: Check out this room from XPulse app!\n\n" + roomInfo;

        // Intent para compartilhar
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Share room info via"));
    }

    // Método para abrir o servidor
    private void openServer(Room room) {
        String serverUrl = room.getServer();
        if (serverUrl != null && !serverUrl.isEmpty()) {
            try {
                Uri uri = Uri.parse(serverUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(RoomScreen.this, "Erro ao abrir o servidor", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RoomScreen.this, "Servidor não disponível", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para adicionar o PlayersFragment dinamicamente
    private void addPlayersFragment() {
        // Cria o fragmento de usuários
        PlayersFragment playersFragment = PlayersFragment.newInstance();

        // Realiza a transação de fragmentos para adicionar o fragmento ao FrameLayout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_players, playersFragment);
        transaction.commit();
    }
}
