package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;

import com.airbnb.lottie.LottieAnimationView;

public class pantalla_carga extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_carga); // Asegúrate de que el XML esté correctamente configurado

        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setSpeed(3); // Establece la velocidad
        lottieAnimationView.playAnimation(); // Inicia la animación

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(pantalla_carga.this, SplashActivityHome.class);
                startActivity(intent);
                finish();
            }
        }, 1500); // 3 segundos de espera
    }
}
