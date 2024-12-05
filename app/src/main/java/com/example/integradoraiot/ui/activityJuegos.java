package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;

public class activityJuegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos);

        // Obtener los datos del intent
        Intent intent = getIntent();
        int kidId = intent.getIntExtra("kid_id", -1);
        String kidName = intent.getStringExtra("kid_name");
        String kidLastName = intent.getStringExtra("kid_lastname");
        int kidAge = intent.getIntExtra("kid_age", 0);

        // Mostrar los datos en la interfaz de usuario (TextViews, etc.)
        TextView nameTextView = findViewById(R.id.tv_titulo_juegos);
        nameTextView.setText("Nombre: " + kidName + " " + kidLastName);
    }
}
