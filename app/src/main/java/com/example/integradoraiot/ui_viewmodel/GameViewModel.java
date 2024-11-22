package com.example.integradoraiot.ui_viewmodel;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.R;
import com.example.integradoraiot.models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameViewModel extends ViewModel {

    private final MutableLiveData<List<Game>> games;

    public GameViewModel() {
        games = new MutableLiveData<>();
        loadGames();
    }

    public LiveData<List<Game>> getGames() {
        return games;
    }

    private void loadGames() {
        List<Game> gameList = new ArrayList<>();
        gameList.add(new Game("AAAAAAAAAAAAAAAAA", R.drawable.sikra_parado));
        games.setValue(gameList);
        Log.d("GameViewModel", "Número de juegos cargados: " + gameList.size());
    }

}
