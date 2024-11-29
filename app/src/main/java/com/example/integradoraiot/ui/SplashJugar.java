package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.integradoraiot.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashJugar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones);

        // Encontramos el botón
        Button btnPlayGame = findViewById(R.id.btnPlayGame);

        // Establecemos el listener para el clic del botón
        btnPlayGame.setOnClickListener(v -> {
            // Acción que ocurre cuando el usuario hace clic en el botón
            Intent intent = new Intent(SplashJugar.this, SplashSeleccionarNiñoActivity.class);
            startActivity(intent);
        });
    }
}
