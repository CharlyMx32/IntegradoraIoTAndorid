package com.example.integradoraiot.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;
import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        EditText NombreEditText = findViewById(R.id.nombre_edit_text);
        EditText CorreoEditText = findViewById(R.id.correo_edit_text);
        EditText ContraseñaEditText = findViewById(R.id.contrasena_edit_text);
        Button registroButton = findViewById(R.id.registrar_button);

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = NombreEditText.getText().toString().trim();
                String correo = CorreoEditText.getText().toString().trim();
                String contrasena = ContraseñaEditText.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                    Toast.makeText(SplashActivityRegistro.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear una instancia de Retrofit y el servicio
                ApiService apiService = RetroFitClient.getClient().create(ApiService.class);

                // Crear el cuerpo de la petición
                RegisterRequest request = new RegisterRequest(correo, contrasena, nombre);

                // Llamar al endpoint de registro
                Call<RegisterResponse> call = apiService.register(request);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Toast.makeText(SplashActivityRegistro.this, "Registro exitoso: " + response.body().getUser().getNombre(), Toast.LENGTH_SHORT).show();

                            // Redirigir al login
                            Intent intent = new Intent(SplashActivityRegistro.this, SplashActivityLogin.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SplashActivityRegistro.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(SplashActivityRegistro.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
