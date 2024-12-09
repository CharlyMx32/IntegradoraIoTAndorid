package com.example.integradoraiot.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.JWT;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.UsuarioRequest;
import com.example.integradoraiot.models.UsuarioResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivityPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_perfil);

        // Obtener el token almacenado
        TokenManager tokenManager = new TokenManager(this);
        String token = tokenManager.getToken();

        // Decodificar token para obtener el ID del usuario
        String idPersona = null;
        if (token != null) {
            JWT jwt = new JWT(token);
            idPersona = jwt.getClaim("id_persona").asString();
        }

        // Hacer la llamada a la API
        if (idPersona != null && token != null) {
            fetchPerfilData(idPersona, "Bearer " + token);
        }

        // Configurar el botón de logout
        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tokenManager.removeToken();

                Intent intent = new Intent(SplashActivityPerfil.this, SplashActivityLogin.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private void fetchPerfilData(String idPersona, String token) {
        ApiService apiService = RetroFitClient.getClientSinToken().create(ApiService.class);

        UsuarioRequest idRequest = new UsuarioRequest(idPersona);

        Call<UsuarioResponse> call = apiService.getPerfil(token, idRequest);

        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UsuarioResponse.Usuario usuario = response.body().getUsuario();

                    TextView emailTextView = findViewById(R.id.email_text);
                    TextView nombreTextView = findViewById(R.id.nombre_text);
                    TextView apellidoTextView = findViewById(R.id.apellido_text);
                    TextView sexoTextView = findViewById(R.id.sexo_text);
                    TextView fechaNacimientoTextView = findViewById(R.id.fecha_nacimiento_text);

                    emailTextView.setText(usuario.getEmail());
                    nombreTextView.setText(usuario.getNombre());
                    apellidoTextView.setText(usuario.getApellido_paterno());
                    sexoTextView.setText(usuario.getSexo());
                    fechaNacimientoTextView.setText(usuario.getFecha_nacimiento());

                    ImageView fotoImageView = findViewById(R.id.foto_image);
                    Picasso.get()
                            .load(usuario.getFoto_perfil()) // URL de la imagen
                            .placeholder(R.drawable.ic_launcher_background) // Imagen mientras carga
                            .error(R.drawable.ic_launcher_foreground) // Imagen en caso de error
                            .into(fotoImageView); // ImageView destino
                } else {
                    if (response.code() == 401) {
                        // Token inválido
                        // Redirigir a la pantalla de login
                        Intent intent = new Intent(SplashActivityPerfil.this, SplashActivityLogin.class);
                        startActivity(intent);

                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                // Manejar errores de red
            }
        });
    }
}
