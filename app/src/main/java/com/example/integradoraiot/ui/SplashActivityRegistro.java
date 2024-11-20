package com.example.integradoraiot.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import java.util.Calendar;

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
        TextView loginText = findViewById(R.id.login_text);
        Spinner sexoSpinner = findViewById(R.id.sexo_spinner);
        TextView fechaNacimientoTextView = findViewById(R.id.fecha_nacimiento_text);

        contrasenaEditText.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());

        // Configurar el comportamiento de las teclas "Enter"
        setupEnterKeyBehavior();

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

        // Configurar el listener para el TextView (loginText)
        loginText.setOnClickListener(v -> {
            Intent intent = new Intent(SplashActivityRegistro.this, SplashActivityLogin.class);
            startActivity(intent);
        });

        fechaNacimientoTextView.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            final Calendar minDate = Calendar.getInstance();
            minDate.set(year - 100, month, day);

            final Calendar maxDate = Calendar.getInstance();
            maxDate.set(year - 18, month, day);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    SplashActivityRegistro.this,
                    (datePicker, selectedYear, selectedMonth, selectedDay) -> {
                        String fechaSeleccionada = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        fechaNacimientoTextView.setText(fechaSeleccionada);
                    },
                    year - 18,
                    month,
                    day
            );

            // Establecer límites para la fecha
            datePickerDialog.getDatePicker().setMinDate(minDate.getTimeInMillis());
            datePickerDialog.getDatePicker().setMaxDate(maxDate.getTimeInMillis());

            // Mostrar el diálogo
            datePickerDialog.show();
        });
    }


    // Configurar la acción de "Enter" para los EditText
    private void setupEnterKeyBehavior() {
        EditText nombreEditText = findViewById(R.id.nombre_edit_text);
        EditText apellidoEditText = findViewById(R.id.apellido_edit_text);
        EditText correoEditText = findViewById(R.id.correo_edit_text);
        EditText contrasenaEditText = findViewById(R.id.contrasena_edit_text);

        setNextFocus(nombreEditText, apellidoEditText);
        setNextFocus(apellidoEditText, correoEditText);
        setNextFocus(correoEditText, contrasenaEditText);
    }

    // Método para establecer el foco en el siguiente EditText
    private void setNextFocus(EditText currentEditText, EditText nextEditText) {
        currentEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                nextEditText.requestFocus();
                return true;
            }
            return false;
        });
    }

    // Método para configurar el listener de "Enter" con opciones adicionales
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

                return true;
            }
            return false;
        });
    }
}
