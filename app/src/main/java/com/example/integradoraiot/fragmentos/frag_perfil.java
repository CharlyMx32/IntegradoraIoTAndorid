package com.example.integradoraiot.fragmentos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

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

public class frag_perfil extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_perfil, container, false);

        // Obtener el token almacenado
        TokenManager tokenManager = new TokenManager(getContext());
        String token = tokenManager.getToken();

        // Decodificar token para obtener el ID del usuario
        String idPersona = null;
        if (token != null) {
            JWT jwt = new JWT(token);
            idPersona = jwt.getClaim("id_persona").asString();
        }

        // Hacer la llamada a la API
        if (idPersona != null && token != null) {
            fetchPerfilData(rootView, "Bearer " + token, idPersona);
        }

        return rootView;
    }

    private void fetchPerfilData(View rootView, String token, String idPersona) {
        ApiService apiService = RetroFitClient.getClientSinToken().create(ApiService.class);

        // Crear el objeto de solicitud
        UsuarioRequest idRequest = new UsuarioRequest(idPersona);

        Call<UsuarioResponse> call = apiService.getPerfil(token, idRequest);

        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UsuarioResponse.Usuario usuario = response.body().getUsuario();

                    // Mostrar datos en los TextViews
                    TextView emailTextView = rootView.findViewById(R.id.email_text);
                    TextView nombreTextView = rootView.findViewById(R.id.nombre_text);
                    TextView apellidoTextView = rootView.findViewById(R.id.apellido_text);
                    TextView sexoTextView = rootView.findViewById(R.id.sexo_text);
                    TextView fechaNacimientoTextView = rootView.findViewById(R.id.fecha_nacimiento_text);

                    emailTextView.setText(usuario.getEmail());
                    nombreTextView.setText(usuario.getNombre());
                    apellidoTextView.setText(usuario.getApellido_paterno());
                    sexoTextView.setText(usuario.getSexo());
                    fechaNacimientoTextView.setText(usuario.getFecha_nacimiento());

                    // Mostrar la imagen de perfil (puedes usar Glide o Picasso)
                    ImageView fotoImageView = rootView.findViewById(R.id.foto_image);
                    Picasso.get()
                            .load(usuario.getFoto_perfil()) // URL de la imagen
                            .placeholder(R.drawable.ic_launcher_background) // Imagen mientras carga
                            .error(R.drawable.ic_launcher_foreground) // Imagen en caso de error
                            .into(fotoImageView); // ImageView destino

                } else {
                    // Manejar error en la respuesta
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                // Manejar errores de red
            }
        });
    }
}
