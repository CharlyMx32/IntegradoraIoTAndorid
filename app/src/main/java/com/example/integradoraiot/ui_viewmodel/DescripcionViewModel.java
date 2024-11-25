package com.example.integradoraiot.ui_viewmodel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.TokenInterceptor;
import com.example.integradoraiot.TokenManager;
import com.example.integradoraiot.models.Descripcion;
import com.example.integradoraiot.network.ApiService;
import com.example.integradoraiot.network.RetroFitClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescripcionViewModel extends ViewModel {
    private final MutableLiveData<List<Descripcion>> descripciones;
    private final ApiService apiService;

    public DescripcionViewModel(Context context) {
        descripciones = new MutableLiveData<>();

        TokenManager tokenManager = new TokenManager(context);
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenManager);

        apiService = RetroFitClient.getClient(tokenInterceptor).create(ApiService.class);
    }

    public LiveData<List<Descripcion>> getDescripciones() {
        return descripciones;
    }

    public void fetchDescripciones() {
        apiService.getDescripciones().enqueue(new Callback<List<Descripcion>>() {
            @Override
            public void onResponse(Call<List<Descripcion>> call, Response<List<Descripcion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    descripciones.postValue(response.body());
                } else {
                    descripciones.postValue(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<Descripcion>> call, Throwable t) {
                descripciones.postValue(Collections.emptyList());
            }
        });
    }
}
