package br.team.xpulse.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.team.xpulse.Utils.ActivitySection.ActivitySelectionFragment;
import br.team.xpulse.Utils.CategorySection.CategoryFragment;
import br.team.xpulse.Model.Activity;
import br.team.xpulse.Model.Room;
import br.team.xpulse.R;

public class AddScreen extends AppCompatActivity {

    private TextView inputActivity;
    private ImageView imgActivity;
    private Activity selectedActivity;
    private EditText serverInput, dateInput, hourInput, descriptionInput;
    private LinearLayout activityInputLayout;

    private static final String TAG = "AddScreen"; // Tag para log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        // Inicializa as views
        inputActivity = findViewById(R.id.input_activity);
        imgActivity = findViewById(R.id.img_activity);
        serverInput = findViewById(R.id.server_input);
        dateInput = findViewById(R.id.date_input);
        hourInput = findViewById(R.id.hour_input);
        descriptionInput = findViewById(R.id.description_input);
        activityInputLayout = findViewById(R.id.activity_input);
    }

    private void setupListeners() {
        // Inicializa o botão de envio
        Button submitButton = findViewById(R.id.btn_add_match);

        // Define o listener de clique para o LinearLayout inteiro
        activityInputLayout.setOnClickListener(v -> showActivitySelectionFragment());

        // Define o listener de clique para o botão de envio
        submitButton.setOnClickListener(v -> submitData());
    }

    private void showActivitySelectionFragment() {
        // Cria e exibe o fragmento para seleção de atividade
        ActivitySelectionFragment fragment = new ActivitySelectionFragment();

        // Adicionando o listener
        fragment.setOnActivitySelectedListener(activity -> {
            selectedActivity = activity; // Define a atividade selecionada
            inputActivity.setText(activity.getActivityName());
            imgActivity.setImageResource(activity.getPhotoID());
        });

        fragment.show(getSupportFragmentManager(), "activity_selection");
    }


    private void submitData() {
        // Obtém os valores dos campos de entrada
        String activityName = inputActivity.getText().toString();
        String server = serverInput.getText().toString();
        String date = dateInput.getText().toString();
        String hour = hourInput.getText().toString();
        String description = descriptionInput.getText().toString();

        // Verifica se os campos estão vazios
        if (activityName.isEmpty() || server.isEmpty() || date.isEmpty() || hour.isEmpty() || description.isEmpty()) {
            // Exibe um Toast informando que os campos não podem estar vazios
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return; // Sai do método se os campos estiverem vazios
        }

        // Verifica se uma atividade foi selecionada
        if (selectedActivity != null) {
            try {
                Date dateTime = createDateTime(date, hour);
                List<String> selectedTags = getSelectedTags();

                // Cria uma nova instância de Room com os dados fornecidos e as tags selecionadas
                Room newRoom = new Room(activityName, server, dateTime, selectedActivity, description, selectedTags);
                Log.d(TAG, "Room Created: " + newRoom);

                // Adiciona a sala criada a um Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("newRoom", newRoom); // Certifique-se de que Room implementa Serializable

                // Cria um Intent para iniciar a HomeScreen
                Intent intent = new Intent(this, HomeScreen.class);
                intent.putExtra("roomBundle", bundle); // Adiciona o Bundle ao Intent

                startActivity(intent); // Inicia a HomeScreen

            } catch (ParseException e) {
                Log.e(TAG, "Erro ao analisar data e hora", e);
                Toast.makeText(this, "Formato de data/hora inválido.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d(TAG, "Nenhuma atividade selecionada");
            Toast.makeText(this, "Por favor, selecione uma atividade.", Toast.LENGTH_SHORT).show();
        }
    }

    private Date createDateTime(String date, String hour) throws ParseException {
        // Combina a data (dd/MM) e a hora (HH:mm) com o ano atual
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

        // Obtém o ano atual
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        // Adiciona o ano atual à data de entrada para completar a data
        String dateTimeString = date + "/" + currentYear + " " + hour;
        return dateTimeFormat.parse(dateTimeString);
    }

    private List<String> getSelectedTags() {
        // Obtém o CategoryFragment para acessar as tags selecionadas
        CategoryFragment categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentById(R.id.category_fragment);
        return categoryFragment != null ? categoryFragment.getSelectedTags() : null;
    }
}
