package com.example.integradoraiot.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.integradoraiot.R;

public class SplashActivityHome extends AppCompatActivity {

    private Button iniciarSesionButton;
    private Button registrarseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);

        iniciarSesionButton = findViewById(R.id.iniciar_sesion_button);
        registrarseButton = findViewById(R.id.registro_home_button);

        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivityHome.this, SplashActivityLogin.class);
                startActivity(intent);
            }
        });

        registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivityHome.this, SplashActivityRegistro.class);
                startActivity(intent);
            }
        });
    }
}

