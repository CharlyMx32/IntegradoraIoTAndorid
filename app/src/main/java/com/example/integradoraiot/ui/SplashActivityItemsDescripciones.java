package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;

public class SplashActivityItemsDescripciones extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones);

        // Referencias a los elementos del layout
        ImageView imgGameIcon = findViewById(R.id.imgGameIconDetail);
        TextView tvGameName = findViewById(R.id.tvGameNameDetail);
        TextView tvGameDescription = findViewById(R.id.tvGameDescription);
        Button btnPlayGame = findViewById(R.id.btnPlayGame);

        // Obtener los datos del Intent
        String gameName = getIntent().getStringExtra("game_name");
        int gameImage = getIntent().getIntExtra("game_icon", 0);
        String gameDescription = getIntent().getStringExtra("game_description");

        // Configurar los elementos con los datos recibidos
        tvGameName.setText(gameName);
        imgGameIcon.setImageResource(gameImage);
        tvGameDescription.setText(gameDescription);

        // Configurar el botón de jugar
        btnPlayGame.setOnClickListener(v ->
                Toast.makeText(this, "¡Jugando " + gameName + "!", Toast.LENGTH_SHORT).show()
        );
    }
}
