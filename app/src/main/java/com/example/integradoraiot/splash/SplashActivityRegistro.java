package com.example.integradoraiot.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;

public class SplashActivityRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText NombreEditText = findViewById(R.id.nombre_edit_text);
        EditText ApellidosEditText = findViewById(R.id.apellido_edit_text);
        EditText TelefonoEditText = findViewById(R.id.telefono_edit_text);
        EditText CorreoEditText = findViewById(R.id.correo_edit_text);
        EditText ContraseñaEditText = findViewById(R.id.contrasena_edit_text);
        Button registroButton = findViewById(R.id.registrar_button);
        Button regresarButton = findViewById(R.id.regreso_button);

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = NombreEditText.getText().toString();
                String Apellidos = ApellidosEditText.getText().toString();
                String Telefono = TelefonoEditText.getText().toString();
                String Correo = CorreoEditText.getText().toString();
                String Contraseña = ContraseñaEditText.getText().toString();

                // (FALTA) código para guardar el usuario, validar campos, etc.

                Intent intent = new Intent(SplashActivityRegistro.this, SplashActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivityRegistro.this, SplashActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
