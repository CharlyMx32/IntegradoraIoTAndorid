package com.example.integradoraiot.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.integradoraiot.MainActivity;
import com.example.integradoraiot.R;

public class SplashActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);

        Button loginButton = findViewById(R.id.iniciar_sesion_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivityLogin.this, SplashActivityVentanas.class);
                startActivity(intent);
                finish();
            }
        });

        TextView registrarseText = findViewById(R.id.registro_text);
        registrarseText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //aqui iba SplashActivityRegistro
                Intent intent = new Intent(SplashActivityLogin.this, SplashActivityRegistro.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
