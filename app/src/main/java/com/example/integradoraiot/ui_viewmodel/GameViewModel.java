package com.example.integradoraiot.ui_viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context; // Asegúrate de incluir esto

public class GameViewModel extends ViewModel {

    private final MutableLiveData<List<Game>> games = new MutableLiveData<>();
    private final ApiService apiService;

    // Constructor
    public GameViewModel(Context context) {
        TokenManager tokenManager = new TokenManager(context);
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenManager);
        apiService = RetroFitClient.getClient(tokenInterceptor).create(ApiService.class);
        fetchGamesFromApi();
    }

    public LiveData<List<Game>> getGames() {
        return games;
    }

    private void fetchGamesFromApi() {
        apiService.getDescripciones().enqueue(new Callback<List<Descripcion>>() {
            @Override
            public void onResponse(Call<List<Descripcion>> call, Response<List<Descripcion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Game> gameList = new ArrayList<>();
                    for (Descripcion descripcion : response.body()) {
                        gameList.add(new Game(
                                descripcion.getName(),
                                descripcion.getDescription()
                        ));
                    }
                    games.setValue(gameList);
                } else {
                    Log.e("GameViewModel", "Error en la respuesta de la API: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Descripcion>> call, Throwable t) {
                Log.e("GameViewModel", "Fallo al consumir la API", t);
            }
        });
    }
}
