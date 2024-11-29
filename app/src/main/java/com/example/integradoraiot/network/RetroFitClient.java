package com.example.integradoraiot.network;

import com.example.integradoraiot.TokenInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {

    private static final String BASE_URL = "https://1549-187-190-56-49.ngrok-free.appz/api/"; // URL de tu API
    private static Retrofit retrofit = null;

    // Método único para obtener el cliente Retrofit con el TokenInterceptor
    public static Retrofit getClient(TokenInterceptor tokenInterceptor) {
        if (retrofit == null) {
            // Crear una instancia de Gson con configuraciones de leniencia (para datos sucios)
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            // Crear un OkHttpClient con el interceptor para agregar el token en las cabeceras
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(tokenInterceptor) // Agregar el interceptor del token
                    .build();

            // Configuración de Retrofit con base URL, conversión JSON y cliente OkHttp con interceptor
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientSinToken() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
