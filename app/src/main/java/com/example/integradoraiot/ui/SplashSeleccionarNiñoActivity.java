package com.example.integradoraiot.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integradoraiot.Adapters.NiñoAdapter;
import com.example.integradoraiot.models.Niño;
import com.example.integradoraiot.R;
import com.example.integradoraiot.ui_viewmodel.NiñoViewModel;

import java.util.List;

public class SplashSeleccionarNiñoActivity extends AppCompatActivity {

    private NiñoViewModel niñoViewModel;
    private RecyclerView recyclerView;
    private NiñoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_nino);

        recyclerView = findViewById(R.id.recyclerViewNinos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        niñoViewModel = new ViewModelProvider(this).get(NiñoViewModel.class);

        // Observa los datos del ViewModel
        niñoViewModel.getListaNiños().observe(this, this::configurarRecyclerView);
    }

    private void configurarRecyclerView(List<Niño> listaNiños) {
        adapter = new NiñoAdapter(listaNiños, niño -> {
            // Acción cuando se selecciona un niño
            Toast.makeText(this, "Seleccionaste a: " + niño.getNombre() + " " + niño.getApellido(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }
}
