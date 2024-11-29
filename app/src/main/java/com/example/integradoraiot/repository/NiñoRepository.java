package com.example.integradoraiot.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.integradoraiot.models.Niño;

import java.util.ArrayList;
import java.util.List;

public class NiñoRepository {
    private static NiñoRepository instance;

    public static NiñoRepository getInstance() {
        if (instance == null) {
            instance = new NiñoRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Niño>> obtenerNiños() {
        MutableLiveData<List<Niño>> data = new MutableLiveData<>();

        // Simulación de datos (puedes reemplazar con una llamada a una API)
        List<Niño> lista = new ArrayList<>();
        lista.add(new Niño("Juan", "Pérez"));
        lista.add(new Niño("María", "López"));
        lista.add(new Niño("Carlos", "García"));

        data.setValue(lista);
        return data;
    }
}

