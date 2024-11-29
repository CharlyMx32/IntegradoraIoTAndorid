package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;

public class SplashActivityItemsDescripciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones);

        ImageView gameImage = findViewById(R.id.imgGameIconDetail);
        TextView gameName = findViewById(R.id.tvGameNameDetail);
        TextView gameDescription = findViewById(R.id.tvGameDescription);

        // Recuperar datos del Intent
        String name = getIntent().getStringExtra("gameName");
        String description = getIntent().getStringExtra("gameDescription");
        int imageResource = getIntent().getIntExtra("gameImage", 0);

        // Asignar valores a los elementos de la vista
        gameName.setText(name);
        gameDescription.setText(description);
        gameImage.setImageResource(imageResource); // O usa Glide si es una URL
    }
}
