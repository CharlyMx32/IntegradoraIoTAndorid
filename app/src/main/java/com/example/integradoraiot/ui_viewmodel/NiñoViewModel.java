package com.example.integradoraiot.ui_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.integradoraiot.R;
import com.example.integradoraiot.models.Niño;
import com.example.integradoraiot.repository.NiñoRepository;

import java.util.List;

public class NiñoViewModel extends ViewModel {
    private MutableLiveData<List<Niño>> listaNiños;
    private NiñoRepository repository;

    public NiñoViewModel() {
        repository = NiñoRepository.getInstance();
        listaNiños = repository.obtenerNiños();
    }

    public LiveData<List<Niño>> getListaNiños() {
        return listaNiños;
    }
}

