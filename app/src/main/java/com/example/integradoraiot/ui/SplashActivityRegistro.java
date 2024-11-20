package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

        // Inicializar los campos de texto
        EditText nombreEditText = findViewById(R.id.nombre_edit_text);
        EditText apellidoEditText = findViewById(R.id.apellido_edit_text);
        EditText correoEditText = findViewById(R.id.correo_edit_text);
        EditText contrasenaEditText = findViewById(R.id.contrasena_edit_text);
        EditText fechaNacimientoTextView = findViewById(R.id.fecha_nacimiento_text);
        Spinner sexoSpinner = findViewById(R.id.sexo_spinner);

        // Configurar el listener para los campos de texto
        setKeyPressListener(nombreEditText, apellidoEditText);  // Cuando se presiona ENTER en nombre, pasa a apellido
        setKeyPressListener(apellidoEditText, correoEditText); // Cuando se presiona ENTER en apellido, pasa a correo
        setKeyPressListener(correoEditText, contrasenaEditText); // Cuando se presiona ENTER en correo, pasa a contraseña

        // Configurar el listener para el botón de registro
        Button registroButton = findViewById(R.id.listo_button);
        registroButton.setOnClickListener(view -> {
            // Obtener los datos
            String nombre = nombreEditText.getText().toString().trim();
            String correo = correoEditText.getText().toString().trim();
            String apellido = apellidoEditText.getText().toString().trim();
            String sexo = sexoSpinner.getSelectedItem().toString().trim();
            String fechaNacimiento = fechaNacimientoTextView.getText().toString().trim();
            String contrasena = contrasenaEditText.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(SplashActivityRegistro.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                return;
            }

            registerViewModel.registerUser(nombre, apellido, sexo, fechaNacimiento, correo, contrasena);
        });
    }

    // Método para configurar el listener de "ENTER"
    private void setKeyPressListener(EditText currentEditText, EditText nextEditText) {
        currentEditText.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Mover el foco al siguiente campo
                nextEditText.requestFocus();
                return true;
            }
            return false;
        });
    }
}