package com.example.integradoraiot.network;

import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
import com.example.integradoraiot.models.Persona;
import com.example.integradoraiot.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("registro")
    Call<RegisterResponse> register(@Body Persona request);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
