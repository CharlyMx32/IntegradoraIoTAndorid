package com.example.integradoraiot.repository;

import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
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

    public interface LoginCallback {
        void onSuccess(String token);
        void onError(String errorMessage);
    }

    public void registerUser(RegisterRequest registerRequest, RegisterCallback callback) {
        Call<RegisterResponse> call = apiService.register(registerRequest);
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

    // Método para iniciar sesión
    public void loginUser(LoginRequest request, LoginCallback callback) {
        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getToken());
                } else {
                    callback.onError("Error en el inicio de sesión: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }
}

