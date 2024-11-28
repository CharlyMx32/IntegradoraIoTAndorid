package com.example.integradoraiot.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {
    private final ApiService apiService;

    public GameRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<List<Game>> getGames() {
        MutableLiveData<List<Game>> data = new MutableLiveData<>();

        apiService.getGame().enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
