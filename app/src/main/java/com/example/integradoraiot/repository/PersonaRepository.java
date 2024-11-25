package com.example.integradoraiot.repository;

import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;
import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaRepository {
    private final ApiService apiService;

    public PersonaRepository(Context context) {
        TokenManager tokenManager = new TokenManager(context);
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenManager);
        apiService = RetroFitClient.getClient(tokenInterceptor).create(ApiService.class);
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
                    // Mejorar manejo de errores
                    String errorMsg = "Error al registrar usuario. Código: " + response.code();
                    if (response.errorBody() != null) {
                        // Obtener detalles del cuerpo de error (si existe)
                        errorMsg += " - " + response.errorBody().toString();
                    }
                    callback.onError(errorMsg);
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }

    public void loginUser(LoginRequest request, LoginCallback callback) {
        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getToken());
                } else {
                    // Mejorar manejo de errores
                    String errorMsg = "Error en el inicio de sesión: " + response.code();
                    if (response.errorBody() != null) {
                        errorMsg += " - " + response.errorBody().toString();
                    }
                    callback.onError(errorMsg);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("Fallo de red: " + t.getMessage());
            }
        });
    }
}
