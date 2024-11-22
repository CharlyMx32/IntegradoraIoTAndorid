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
        gameList.add(new Game("Juego 1", R.drawable.sikra_parado));
        gameList.add(new Game("Juego 2", R.drawable.sikra_parado_apuntando));
        gameList.add(new Game("Juego 3", R.drawable.sikra_parado_ojos));
        gameList.add(new Game("Juego 4", R.drawable.sikra_sentado));
        gameList.add(new Game("Juego 5", R.drawable.sikra_bolita));
        games.setValue(gameList);
    }

}
