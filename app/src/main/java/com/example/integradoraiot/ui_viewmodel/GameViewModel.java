package com.example.integradoraiot.ui_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;
import com.example.integradoraiot.repository.GameRepository;

import java.util.List;

public class GameViewModel extends ViewModel {
    private final GameRepository repository;

    public GameViewModel() {
        repository = new GameRepository(RetroFitClient.getClientSinToken().create(ApiService.class));
    }

    public LiveData<List<Game>> getGames() {
        return repository.getGames();
    }
}
