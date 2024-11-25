package com.example.integradoraiot.ui_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescripcionViewModel extends ViewModel {
    // MutableLiveData que contiene la lista de descripciones
    private final MutableLiveData<List<Descripcion>> descripciones;
    // Servicio de API para realizar la conexión a la red
    private final ApiService apiService;

    // Constructor que inicializa las variables
    public DescripcionViewModel() {
        descripciones = new MutableLiveData<>();
        apiService = RetroFitClient.getClient().create(ApiService.class);
    }

    // Método para exponer el LiveData al observador
    public LiveData<List<Descripcion>> getDescripciones() {
        return descripciones;
    }

    // Método para obtener las descripciones desde la API
    public void fetchDescripciones() {
        apiService.getDescripciones().enqueue(new Callback<List<Descripcion>>() {
            @Override
            public void onResponse(Call<List<Descripcion>> call, Response<List<Descripcion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Actualiza el MutableLiveData con la lista obtenida
                    descripciones.postValue(response.body());
                } else {
                    // Si la respuesta no es exitosa, envía una lista vacía
                    descripciones.postValue(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<Descripcion>> call, Throwable t) {
                // En caso de error en la llamada, envía una lista vacía
                descripciones.postValue(Collections.emptyList());
            }
        });
    }
}
