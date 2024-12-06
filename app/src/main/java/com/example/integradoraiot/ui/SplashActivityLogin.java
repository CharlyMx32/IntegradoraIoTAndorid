package com.example.integradoraiot.ui;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.integradoraiot.MainActivity;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.ui_viewmodel.PersonaViewModel;
import com.example.integradoraiot.factory.PersonaViewModelFactory;

public class SplashActivityLogin extends AppCompatActivity {

    private PersonaViewModel personaViewModel;
    private String token;
    private TokenManager tokenManager;
    private boolean isBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);

        tokenManager = new TokenManager(this);

        String token = tokenManager.getToken();
        if (token != null) {
            Intent intent = new Intent(SplashActivityLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);

        EditText emailEditText = findViewById(R.id.correo_edit_text);
        EditText passwordEditText = findViewById(R.id.contrasena_edit_text);
        Button loginButton = findViewById(R.id.iniciar_sesion_button);
        TextView olvideTextView = findViewById(R.id.olvide_text);

        passwordEditText.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());

        loginButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                personaViewModel.loginUser(email, password);
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        olvideTextView.setOnClickListener(view -> {
            Intent intent = new Intent(SplashActivityLogin.this, SplashActivityOlvide.class);
            startActivity(intent);
        });

        personaViewModel = new ViewModelProvider(this, new PersonaViewModelFactory(getApplication())).get(PersonaViewModel.class);

        personaViewModel.getLoginSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                Intent intent = new Intent(SplashActivityLogin.this, SplashActivityVentanas.class);
                startActivity(intent);
                finish();
            }
        });

        personaViewModel.getLoginError().observe(this, errorMessage -> {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onBackPressed() {
        if (isBackPressedOnce) {
            super.onBackPressed();
            finishAffinity();
            return;
        }

        this.isBackPressedOnce = true;
        Toast.makeText(this, "Presiona atrás nuevamente para salir", Toast.LENGTH_SHORT).show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBackPressedOnce = false;
            }
        }, 2000); // 2 segundos
    }
}

