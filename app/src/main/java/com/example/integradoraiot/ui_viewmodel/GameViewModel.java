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

    private final MutableLiveData<List<Game>> games = new MutableLiveData<>();

    public GameViewModel() {
        loadGames(); // Inicialización de juegos estáticos
    }

    public LiveData<List<Game>> getGames() {
        return games;
    }

    private void loadGames() {
        List<Game> gameList = new ArrayList<>();
        gameList.add(new Game(
                "Simon dice",
                R.drawable.simon_dice,
                "Un clásico juego de memoria y atención, donde debes seguir las secuencias de colores y sonidos que 'Simón' indica. ¡Cada ronda es más desafiante!"
        ));
        gameList.add(new Game(
                "Cazador de colores",
                R.drawable.cazador_colores,
                "Desarrolla tu rapidez y precisión en este juego donde tendrás que identificar y tocar los colores correctos antes de que desaparezcan."
        ));
        gameList.add(new Game(
                "Luz verde, luz roja",
                R.drawable.semaforo,
                "Un emocionante juego de estrategia donde debes avanzar cuando la luz está verde, pero detenerte completamente en la roja. ¡Cuidado con moverte en el momento equivocado!"
        ));
        gameList.add(new Game(
                "Laberinto",
                R.drawable.laberinto,
                "Explora y resuelve intrincados laberintos, utilizando lógica y paciencia para encontrar la salida. ¡Perfecto para relajarte mientras desafías tu mente!"
        ));
        games.setValue(gameList);
    }
}
