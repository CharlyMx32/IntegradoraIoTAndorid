package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.view.KeyEvent;
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
        EditText correoEditText = findViewById(R.id.correo_edit_text);
        EditText contrasenaEditText = findViewById(R.id.contrasena_edit_text);
        EditText apellidoEditText = findViewById(R.id.apellido_edit_text);
        Spinner sexoSpinner = findViewById(R.id.sexo_spinner);
        TextView fechaNacimientoTextView = findViewById(R.id.fecha_nacimiento_text);

        // Establecer el comportamiento de contraseña
        contrasenaEditText.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());

        // Configurar los listeners de Enter
        setEnterActionListener(nombreEditText, correoEditText);
        setEnterActionListener(correoEditText, contrasenaEditText);
        setEnterActionListener(contrasenaEditText, apellidoEditText);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getIsSuccess().observe(this, isSuccess -> {
            if (isSuccess) {
                // Redirigir al login
                // Código para redirigir al login
            }
        });

        registerViewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(SplashActivityRegistro.this, error, Toast.LENGTH_SHORT).show();
        });

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

    // Método para configurar el listener de "Enter"
    private void setEnterActionListener(EditText currentEditText, final EditText nextEditText) {
        currentEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {

                // Limpiar el campo actual
                currentEditText.setText("");

                // Mover al siguiente campo (si existe)
                nextEditText.requestFocus();

                // Cerrar el teclado
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(currentEditText.getWindowToken(), 0);
                }

                return true; // Indica que hemos manejado el evento
            }
            return false;
        });
    }
}
