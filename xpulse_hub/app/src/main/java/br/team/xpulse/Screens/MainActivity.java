package br.team.xpulse.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;

import br.team.xpulse.R;

public class MainActivity extends AppCompatActivity {

    private Button btnloginDisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);
        btnloginDisc = findViewById(R.id.login_disc);
        btnloginDisc.setOnClickListener(v -> {
           Intent intent = new Intent(MainActivity.this, HomeScreen.class);

           startActivity(intent);
        });


    }
}