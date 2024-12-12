package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.MainActivity;
import com.example.integradoraiot.R;
import com.example.integradoraiot.models.estadisticas;

public class activityestadisticas extends AppCompatActivity {

    private TextView nivelTextView, puntuacionTextView, tiempoTextView;
    private Button btnIrASplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_final);

        nivelTextView = findViewById(R.id.tv_nivel);
        puntuacionTextView = findViewById(R.id.tv_mejor_puntuacion);
        tiempoTextView = findViewById(R.id.tv_tiempo_jugado);
        btnIrASplash = findViewById(R.id.btn_volver);

        // Obtener las estadísticas pasadas por Intent
        if (getIntent().hasExtra("estadisticas")) {
            estadisticas stats = (estadisticas) getIntent().getSerializableExtra("estadisticas");

            // Mostrar las estadísticas
            nivelTextView.setText("Nivel: " + stats.getNivelActual());
            puntuacionTextView.setText("Mejor Puntuación: " + stats.getMejorPuntuacion());
            tiempoTextView.setText("Tiempo Jugado: " + stats.getTiempoJugado());
        }

        // Configurar el botón para ir al SplashActivity
        btnIrASplash.setOnClickListener(v -> {
            Intent intent = new Intent(activityestadisticas.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
