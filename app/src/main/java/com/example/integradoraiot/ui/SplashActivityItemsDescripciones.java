package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.integradoraiot.R;

public class SplashActivityItemsDescripciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_cards_juegos); // Cambia al diseño de tu actividad principal.

        // Encuentra el CardView y el TextView
        CardView cardView = findViewById(R.id.cardView); // ID del CardView en el XML.
        TextView textView = findViewById(R.id.tvGameName);

        // Configura un OnClickListener para el CardView
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en el CardView
                Toast.makeText(SplashActivityItemsDescripciones.this, "CardView clickeado", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura un OnClickListener para el TextView
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al hacer clic en el TextView
                Toast.makeText(SplashActivityItemsDescripciones.this, "TextView clickeado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
