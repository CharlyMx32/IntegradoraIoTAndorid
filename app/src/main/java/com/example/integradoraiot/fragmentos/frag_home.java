package com.example.integradoraiot.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.Adapters.GameAdapter;
import com.example.integradoraiot.R;
import com.example.integradoraiot.models.Game;
import com.example.integradoraiot.ui.SplashActivityItemsDescripciones;
import com.example.integradoraiot.ui_viewmodel.GameViewModel;

import java.util.ArrayList;

public class frag_home extends Fragment {

    private RecyclerView recyclerViewGames;
    private GameAdapter adapter;
    private GameViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configura RecyclerView
        recyclerViewGames = view.findViewById(R.id.recyclerViewGames);
        recyclerViewGames.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Inicializa adaptador con un listener
        adapter = new GameAdapter(new ArrayList<>(), this::onGameClick);
        recyclerViewGames.setAdapter(adapter);

        // Configura ViewModel
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        viewModel.getGames().observe(getViewLifecycleOwner(), gameList -> {
            adapter = new GameAdapter(gameList, this::onGameClick);
            recyclerViewGames.setAdapter(adapter);
        });
    }

    // Método para manejar clics en los juegos
    private void onGameClick(Game game) {
        Intent intent = new Intent(getContext(), SplashActivityItemsDescripciones.class);
        intent.putExtra("gameName", game.getNombre());
        intent.putExtra("gameDescription", game.getDescripcion());
        startActivity(intent);
    }
}
