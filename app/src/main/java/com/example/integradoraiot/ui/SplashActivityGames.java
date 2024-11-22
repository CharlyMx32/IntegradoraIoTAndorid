package com.example.integradoraiot.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.Adapters.GameAdapter;
import com.example.integradoraiot.R;
import com.example.integradoraiot.ui_viewmodel.GameViewModel;

import java.util.ArrayList;

public class SplashActivityGames extends AppCompatActivity {

    private GameViewModel viewModel;
    private GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Configurar RecyclerView
        RecyclerView recyclerViewGames = findViewById(R.id.recyclerViewGames);
        recyclerViewGames.setLayoutManager(new GridLayoutManager(this, 2)); // Dos columnas

        // Inicializa con un adaptador vacío
        adapter = new GameAdapter(new ArrayList<>());
        recyclerViewGames.setAdapter(adapter);

        // Configurar ViewModel
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);

        // Observar cambios en los juegos
        viewModel.getGames().observe(this, gameList -> {
            adapter = new GameAdapter(gameList); // Si deseas, usa notifyDataSetChanged()
            recyclerViewGames.setAdapter(adapter); // Actualiza el adaptador
        });
    }
}

