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

import java.text.SimpleDateFormat;
import java.util.Date;

import br.team.xpulse.Model.Room;
import br.team.xpulse.R;

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
                btnShare.setOnClickListener(v -> {
                    // Chamando o método de compartilhamento
                    shareRoom(room);
                });

                btnBack.setOnClickListener(v -> {
                    finish();
                });

                btnDisc.setOnClickListener(v -> {
                    // Pegando a URL do servidor diretamente de room.getServer()
                    String serverUrl = room.getServer();  // Assume que getServer() retorna uma URL

                    // Verifica se a URL não é nula ou vazia
                    if (serverUrl != null && !serverUrl.isEmpty()) {
                        try {
                            // Tenta converter a URL em um Uri
                            Uri uri = Uri.parse(serverUrl);

                            // Cria a Intent para abrir a URL no navegador
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                            // Tenta iniciar a Activity para abrir a URL
                            startActivity(intent);
                        } catch (Exception e) {
                            // Caso haja algum erro (ex: formato inválido de URL), exibe um Toast
                            Toast.makeText(RoomScreen.this, "Erro ao abrir o servidor", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Caso o serverUrl seja nulo ou vazio
                        Toast.makeText(RoomScreen.this, "Servidor não disponível", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        }
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
                // Caso a atividade não corresponda a nenhuma das opções acima, você pode colocar uma imagem padrão
                roomImage.setImageResource(R.drawable.ic_calendar);
                break;
        }
    }

    // Método para compartilhar as informações da sala
    private void shareRoom(Room room) {
        // Criando a string de texto a ser compartilhada
        String roomInfo = "Room Info:\n" +
                "Name: " + room.getName() + "\n" +
                "Activity: " + room.getActivity().getActivityName() + "\n" +
                "Description: " + room.getDescription() + "\n" +
                "Tags: " + String.join(", ", room.getTags()) + "\n";

        // Formatando a data para "dia/mês - hora:minuto"
        Date date = room.getDateTime();  // Supondo que getDateTime() retorne um objeto Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");  // Formato desejado
        String formattedDate = dateFormat.format(date);

        roomInfo += "Date: " + formattedDate;

        // Texto de compartilhamento
        String shareText = "XPulse: Check out this room from XPulse app!\n\n" + roomInfo;

        // Criando o Intent de compartilhamento
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");  // Tipo de conteúdo (texto simples)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);  // Passando o texto para o Intent

        // Abrindo o seletor de apps para o usuário escolher onde compartilhar
        startActivity(Intent.createChooser(shareIntent, "Share room info via"));
    }
}
