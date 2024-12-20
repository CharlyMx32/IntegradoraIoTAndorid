package com.example.integradoraiot.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.MainActivity;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenManager;

public class SplashActivityHome extends AppCompatActivity {

    private Button iniciarSesionButton;
    private Button registrarseButton;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_home);

        // Verificar si ya hay un token guardado
        tokenManager = new TokenManager(this);

        String token = tokenManager.getToken();
        if (token != null) {
            Intent intent = new Intent(SplashActivityHome.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;

        } else {
            // Si no hay token, mostrar la pantalla de inicio
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


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Acción de retroceso bloqueada temporalmente", Toast.LENGTH_SHORT).show();
        //no se llama a super.onBackPressed() para bloquear la acción de retroceso
    }

}

