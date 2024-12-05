package com.example.integradoraiot.network;

import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;
import com.example.integradoraiot.models.RespuestaRestablecer;
import com.example.integradoraiot.models.RestablecerRequest;
import com.example.integradoraiot.models.KidRequest;
import com.example.integradoraiot.models.UsuarioRequest;
import com.example.integradoraiot.models.UsuarioResponse;

import java.util.List; // Importa la clase List

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("registro")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("obtenerJuegos")
    Call<List<Descripcion>> getDescripciones();

    @GET("tutores")
    Call<ApiResponse> getKids(@Header("Authorization") String token);

    @POST("alta")
    Call<ApiResponse> altaKid(@Header("Authorization") String token, @Body KidRequest request);

    @GET("obtenerJuegos")
    Call<List<Game>> getGame();

    @POST("restablecer")
    Call<RespuestaRestablecer> restablecerContrasena(@Body RestablecerRequest request);

    @POST("perfil")
    Call<UsuarioResponse> getPerfil(@Header("Authorization") String token, @Body UsuarioRequest request);

}
