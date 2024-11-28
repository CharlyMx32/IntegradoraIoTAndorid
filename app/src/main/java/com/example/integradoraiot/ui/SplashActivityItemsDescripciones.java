package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.integradoraiot.R;
import com.example.integradoraiot.fragmentos.KidsFragment;
import com.example.integradoraiot.fragmentos.SeleccionarKidsFragment;

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

        Button btnPlayGame = findViewById(R.id.btnPlayGame);
        CardView cardFragmentContainer = findViewById(R.id.card_fragment_container);

        btnPlayGame.setOnClickListener(v -> {
            // Mostrar el contenedor como una tarjeta
            cardFragmentContainer.setVisibility(View.VISIBLE);

            // Opcional: Cargar un fragmento en el contenedor
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SeleccionarKidsFragment())
                    .commit();
        });

    }
}
