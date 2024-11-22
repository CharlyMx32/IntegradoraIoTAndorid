package com.example.integradoraiot.network;

import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;

import java.util.List; // Importa la clase List

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("registro")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("Juegos")
    Call<List<Descripcion>> getDescripciones(); // Asegúrate de que List esté importado
}
