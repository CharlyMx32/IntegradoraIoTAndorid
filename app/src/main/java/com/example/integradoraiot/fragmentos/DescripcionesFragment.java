package com.example.integradoraiot.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.Adapters.DescripcionAdapter;
import com.example.integradoraiot.R;
import com.example.integradoraiot.ui_viewmodel.DescripcionViewModel;

public class DescripcionesFragment extends Fragment {
    private DescripcionViewModel descripcionViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_descripcion, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adaptador
        final DescripcionAdapter adapter = new DescripcionAdapter(getContext());
        recyclerView.setAdapter(adapter);

        // ViewModel
        descripcionViewModel = new ViewModelProvider(this).get(DescripcionViewModel.class);
        descripcionViewModel.getDescripciones().observe(getViewLifecycleOwner(), adapter::submitList);

        // Llamar a la API
        descripcionViewModel.fetchDescripciones();



        return view;
    }
}
