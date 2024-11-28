package com.example.integradoraiot.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.Adapters.GameAdapter;
import com.example.integradoraiot.R;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.ui_viewmodel.GameViewModel;

import java.util.ArrayList;

public class SplashActivityGames extends AppCompatActivity {

    private GameViewModel viewModel;
    private GameAdapter adapter;

    public interface OnGameClickListener {
        void onGameClick(Game game);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView recyclerViewGames = findViewById(R.id.recyclerViewGames);
        recyclerViewGames.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerViewGames.setHasFixedSize(true);

        adapter = new GameAdapter(new ArrayList<>(), game -> {
            Intent intent = new Intent(SplashActivityGames.this, SplashActivityItemsDescripciones.class);
            intent.putExtra("gameName", game.getName());
            intent.putExtra("gameDescription", game.getDescripcion());
            startActivity(intent);
        });

        recyclerViewGames.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        viewModel.getGames().observe(this, gameList -> {
        });
    }


}

