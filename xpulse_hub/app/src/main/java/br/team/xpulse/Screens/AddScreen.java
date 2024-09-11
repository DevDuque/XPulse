package br.team.xpulse.Screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.team.xpulse.ActivitySection.ActivitySelectionFragment;
import br.team.xpulse.Model.Activity;
import br.team.xpulse.Model.Room;
import br.team.xpulse.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddScreen extends AppCompatActivity {

    private TextView inputActivity;
    private ImageView imgActivity;
    private Activity selectedActivity;
    private EditText serverInput, dateInput, hourInput, descriptionInput;
    private LinearLayout activityInputLayout;

    private static final String TAG = "AddScreen"; // Tag para logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);

        // Inicializa as views
        inputActivity = findViewById(R.id.input_activity);
        imgActivity = findViewById(R.id.img_activity);
        serverInput = findViewById(R.id.server_input);
        dateInput = findViewById(R.id.date_input);
        hourInput = findViewById(R.id.hour_input);
        descriptionInput = findViewById(R.id.description_input);
        activityInputLayout = findViewById(R.id.activity_input);

        // Inicializa o botão de envio
        Button submitButton = findViewById(R.id.btn_add_match);

        // Define o listener de clique para o LinearLayout inteiro
        activityInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria e exibe o fragmento para seleção de atividades
                ActivitySelectionFragment fragment = new ActivitySelectionFragment();
                fragment.setOnActivitySelectedListener(new ActivitySelectionFragment.OnActivitySelectedListener() {
                    @Override
                    public void onActivitySelected(Activity activity) {
                        selectedActivity = activity; // Define a atividade selecionada
                        inputActivity.setText(activity.getActivityName());
                        imgActivity.setImageResource(activity.getPhotoID());
                    }
                });
                fragment.show(getSupportFragmentManager(), "activity_selection");
            }
        });

        // Define o listener de clique para o botão de envio
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém os valores dos campos de entrada
                String activityName = inputActivity.getText().toString();
                String server = serverInput.getText().toString();
                String date = dateInput.getText().toString();
                String hour = hourInput.getText().toString();
                String description = descriptionInput.getText().toString();

                // Verifica se uma atividade foi selecionada
                if (selectedActivity != null) {
                    try {
                        // Combina a data e a hora em um único formato
                        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                        String dateTimeString = date + " " + hour;
                        Date dateTime = dateTimeFormat.parse(dateTimeString);

                        // Cria uma nova instância de Room com os dados fornecidos
                        Room newRoom = new Room(activityName, "Categoria", server, dateTime, selectedActivity, description);
                        Log.d(TAG, "Sala Criada: " + newRoom.toString());

                        // Ações adicionais, AQUI
                    } catch (ParseException e) {
                        Log.e(TAG, "Erro ao analisar data e hora", e);
                    }
                } else {
                    Log.d(TAG, "Nenhuma atividade selecionada");
                }
            }
        });
    }
}
