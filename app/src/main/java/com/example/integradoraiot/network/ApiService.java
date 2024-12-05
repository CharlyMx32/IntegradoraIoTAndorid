package com.example.integradoraiot.network;

import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;
import com.example.integradoraiot.models.KidRequest;

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

    @GET("obtenerJuegos")
    Call<GameResponse> getGame();

    @GET("tutores")
    Call<ApiResponse> getKids(@Header("Authorization") String token);

    @POST("alta")
    Call<ApiResponse> altaKid(@Header("Authorization") String token, @Body KidRequest request);

    public class GameResponse {
        private List<Game> juegos;

        public List<Game> getJuegos() {
            return juegos;
        }

        public void setJuegos(List<Game> juegos) {
            this.juegos = juegos;
        }
    }


}

