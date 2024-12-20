package com.example.integradoraiot.network;


import java.util.HashMap;

import com.example.integradoraiot.models.Adafruit;
import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.models.KidRequest2;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.models.LoginResponse;
import com.example.integradoraiot.models.NewResponse;
import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.RegisterResponse;
import com.example.integradoraiot.models.RespuestaRestablecer;
import com.example.integradoraiot.models.RestablecerRequest;
import com.example.integradoraiot.models.KidRequest;
import com.example.integradoraiot.models.UsuarioRequest;
import com.example.integradoraiot.models.UsuarioResponse;

import java.util.List; // Importa la clase List

import okhttp3.RequestBody;
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
  
    @POST("restablecer")
    Call<RespuestaRestablecer> restablecerContrasena(@Body RestablecerRequest request);

    @POST("perfil")
    Call<UsuarioResponse> getPerfil(@Header("Authorization") String token, @Body UsuarioRequest request);

    public class GameResponse {
        private List<Game> juegos;

        public List<Game> getJuegos() {
            return juegos;
        }

        public void setJuegos(List<Game> juegos) {
            this.juegos = juegos;
        }
    }

    @POST("iniciar")
    Call<ApiResponse> enviarDatosJuego(@Header("Authorization") String token, @Body HashMap<String, String> datos);

    @GET("terminar")
    Call<ApiResponse> obtenerEstadisticas();

    @GET("api/v2/EquipoIoT/feeds/game-status")
    Call<Adafruit> getFeedStatus();

    @POST("v2/iniciar")
    Call<ApiResponse> enviarDatosJuegoDos(@Header("Authorization") String token, @Body HashMap<String, String> datos);

    @GET("v2/terminar")
    Call<ApiResponse> obtenerEstadisticasDos();

    @GET("api/v2/EquipoIoT/feeds/game-status-two")
    Call<Adafruit> getFeedStatusDos();

    @POST("generales")
    Call<NewResponse> getGenerales(@Body KidRequest2 request);
}

