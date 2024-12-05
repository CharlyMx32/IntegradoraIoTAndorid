package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.integradoraiot.R;
import com.example.integradoraiot.models.RestablecerRequest;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.models.RespuestaRestablecer;
import com.example.integradoraiot.network.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityOlvide extends AppCompatActivity {

    private EditText emailEditText;
    private Button restablecerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_olvide);

        emailEditText = findViewById(R.id.email_edit_text);
        restablecerButton = findViewById(R.id.iniciar_sesion_button);

        restablecerButton.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();

            if (validarEmail(email)) {
                // Llamar al servicio para restablecer la contraseña
                restablecerContrasena(email);
            }
        });
    }

    private boolean validarEmail(String email) {
        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void restablecerContrasena(String email) {
        ApiService apiService = RetroFitClient.getClientSinToken().create(ApiService.class);

        // Crear el objeto RestablecerRequest
        RestablecerRequest request = new RestablecerRequest(email);

        // Llamada a la API para restablecer la contraseña
        Call<RespuestaRestablecer> call = apiService.restablecerContrasena(request);

        call.enqueue(new Callback<RespuestaRestablecer>() {
            @Override
            public void onResponse(Call<RespuestaRestablecer> call, Response<RespuestaRestablecer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RespuestaRestablecer respuesta = response.body();
                    if (respuesta.isExito()) {
                        Toast.makeText(SplashActivityOlvide.this, "Revisa tu correo para restablecer la contraseña", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SplashActivityOlvide.this, SplashActivityRestablecido.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SplashActivityOlvide.this, respuesta.getMensaje(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SplashActivityOlvide.this, "Hubo un error al procesar la solicitud", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaRestablecer> call, Throwable t) {
                Toast.makeText(SplashActivityOlvide.this, "Error en la conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
