package com.example.integradoraiot.repository;

import com.example.integradoraiot.models.Persona;
import com.example.integradoraiot.models.RegisterResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaRepository {
    private final ApiService apiService;

    public PersonaRepository() {
        apiService = RetroFitClient.getClient().create(ApiService.class);
    }

    public interface RegisterCallback {
        void onSuccess();
        void onError(String message);
    }

    public void registerUser(Persona persona, RegisterCallback callback) {
        Call<RegisterResponse> call = apiService.register(persona);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess();
                } else {
                    callback.onError("Error al registrar");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }
        });
    }
}

