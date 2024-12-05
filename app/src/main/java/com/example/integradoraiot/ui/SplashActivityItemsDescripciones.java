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
import com.squareup.picasso.Picasso;

public class SplashActivityItemsDescripciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones);

        ImageView gameImage = findViewById(R.id.imgGameIcon);
        TextView gameName = findViewById(R.id.tvGameNameDetail);
        TextView gameDescription = findViewById(R.id.tvGameDescription);

        String name = getIntent().getStringExtra("gameName");
        String description = getIntent().getStringExtra("gameDescription");
        String imageUrl = getIntent().getStringExtra("gameImage"); // Cambia a String

        gameName.setText(name);
        gameDescription.setText(description);

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.reloj) // Imagen de carga
                .error(R.drawable.ic_launcher_background) // Imagen de error
                .into(gameImage); // ImageView objetivo

        Button btnPlayGame = findViewById(R.id.btnPlayGame);
        CardView cardFragmentContainer = findViewById(R.id.card_fragment_container);

        btnPlayGame.setOnClickListener(v -> {
            cardFragmentContainer.setVisibility(View.VISIBLE);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SeleccionarKidsFragment())
                    .commit();
        });
    }
}
