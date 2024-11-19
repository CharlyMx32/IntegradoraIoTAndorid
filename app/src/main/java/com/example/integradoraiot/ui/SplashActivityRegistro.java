package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.integradoraiot.R;
import com.example.integradoraiot.ui_viewmodel.RegisterViewModel;

public class SplashActivityRegistro extends AppCompatActivity {

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getIsSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                // Redirigir al login
                Intent intent = new Intent(SplashActivityRegistro.this, SplashActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });

        registerViewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(SplashActivityRegistro.this, error, Toast.LENGTH_SHORT).show();
        });

        Button registroButton = findViewById(R.id.listo_button);
        registroButton.setOnClickListener(view -> {
            // Obtener los datos
            String nombre = ((EditText) findViewById(R.id.nombre_edit_text)).getText().toString().trim();
            String correo = ((EditText) findViewById(R.id.correo_edit_text)).getText().toString().trim();
            String apellido = ((EditText) findViewById(R.id.apellido_edit_text)).getText().toString().trim();
            String sexo = ((Spinner) findViewById(R.id.sexo_spinner)).toString().trim();
            String fechaNacimiento = ((TextView) findViewById(R.id.fecha_nacimiento_text)).getText().toString().trim();
            String contrasena = ((EditText) findViewById(R.id.contrasena_edit_text)).getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(SplashActivityRegistro.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            registerViewModel.registerUser(nombre, apellido, sexo, fechaNacimiento, correo, contrasena);
        });
    }
}

