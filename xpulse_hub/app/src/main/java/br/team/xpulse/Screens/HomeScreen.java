package br.team.xpulse.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

import br.team.xpulse.R;

public class HomeScreen extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    ImageButton btnAdd;

    ImageView icUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_home_screen);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        btnAdd = findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(HomeScreen.this, AddScreen.class);

            startActivity(intent);
        });

        icUser = findViewById(R.id.user_profile);

        icUser.setOnClickListener(v -> {

            Log.d("FirebaseAnalytics", "icUser clicked!");

            String id = "";
            String name = "";

            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");

            Log.d("FirebaseAnalytics", "Bundle Content: " +
                    "ITEM_ID: " + bundle.getString(FirebaseAnalytics.Param.ITEM_ID) + ", " +
                    "ITEM_NAME: " + bundle.getString(FirebaseAnalytics.Param.ITEM_NAME) + ", " +
                    "CONTENT_TYPE: " + bundle.getString(FirebaseAnalytics.Param.CONTENT_TYPE));

            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        });


    }
}