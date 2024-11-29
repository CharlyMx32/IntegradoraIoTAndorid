package com.example.integradoraiot.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auth0.android.jwt.JWT;
import com.example.integradoraiot.R;
import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.KidRequest;
import com.example.integradoraiot.network.ApiResponse;
import com.example.integradoraiot.network.ApiResponseKids;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_kids extends Fragment {
    private TokenManager tokenManager;
    private String token;
    private String idPersona;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_ninos, container, false);

        // Obtener el token de SharedPreferences
        tokenManager = new TokenManager(getContext());
        token = tokenManager.getToken(); // Obtiene el token almacenado

        // Decodificar el token para obtener el id_persona
        if (token != null) {
            idPersona = decodeTokenAndGetIdPersona(token);
        }

        // Aquí puedes configurar el formulario para registrar al niño
        setupForm(rootView);

        return rootView;
    }

    private String decodeTokenAndGetIdPersona(String token) {
        try {
            // Decodificar el token y obtener el payload
            JWT jwt = new JWT(token);
            // Obtener el campo 'id_persona' desde el payload del JWT
            String id_persona = jwt.getClaim("id_persona").asString();
            return id_persona;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setupForm(View rootView) {
        EditText niñoNombre = rootView.findViewById(R.id.niño_nombre_edit_text);
        EditText niñoApellido = rootView.findViewById(R.id.niño_apellido_edit_text);
        EditText niñoEdad = rootView.findViewById(R.id.niño_edad_edit_text);
        Spinner sexoSpinner = rootView.findViewById(R.id.sexo_spinner);
        Button listoButton = rootView.findViewById(R.id.listo_button);

        listoButton.setOnClickListener(v -> {
            String nombre_kid = niñoNombre.getText().toString();
            String apellido_paterno_kid = niñoApellido.getText().toString();
            int edad_kid = Integer.parseInt(niñoEdad.getText().toString());
            String genero_kid = sexoSpinner.getSelectedItem().toString();

            registerChild(nombre_kid, apellido_paterno_kid, edad_kid, genero_kid);
        });
    }
    
    private void registerChild(String nombre_kid, String apellido_paterno_kid, int edad_kid, String genero_kid) {
        // Crear el objeto KidRequest con los datos necesarios
        KidRequest kidRequest = new KidRequest(idPersona, nombre_kid, apellido_paterno_kid, edad_kid, genero_kid);

        // Configurar Retrofit y la llamada a la API
        tokenManager = new TokenManager(getContext());
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenManager);
        ApiService apiService = RetroFitClient.getClient(tokenInterceptor).create(ApiService.class);

        Call<ApiResponse> call = apiService.altaKid("Bearer " + token, kidRequest);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        // Manejar respuesta exitosa
                        String status = apiResponse.getStatus();
                        String message = apiResponse.getMessage();
                        // Mostrar un mensaje de éxito al usuario
                    }
                } else {
                    // Manejar error en la respuesta
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar errores de red
            }
        });
    }



}

