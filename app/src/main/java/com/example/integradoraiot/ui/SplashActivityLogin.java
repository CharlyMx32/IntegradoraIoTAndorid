package com.example.integradoraiot.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.integradoraiot.R;
import com.example.integradoraiot.ui_viewmodel.PersonaViewModel;
import com.example.integradoraiot.factory.PersonaViewModelFactory;

public class SplashActivityLogin extends AppCompatActivity {

    private PersonaViewModel personaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);

        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);


        EditText emailEditText = findViewById(R.id.correo_edit_text);
        EditText passwordEditText = findViewById(R.id.contrasena_edit_text);
        Button loginButton = findViewById(R.id.iniciar_sesion_button);

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

        personaViewModel = new ViewModelProvider(this, new PersonaViewModelFactory(getApplication())).get(PersonaViewModel.class);

        // Observa los cambios del LiveData
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


        personaViewModel.getLoginError().observe(this, errorMessage -> {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        });
    }

    private void guardarToken(String token) {
        SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    //destruir el token
    private void eliminarToken() {
        SharedPreferences preferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("token");
        editor.apply();
    }



}
