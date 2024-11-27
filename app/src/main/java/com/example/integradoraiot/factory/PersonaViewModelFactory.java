package com.example.integradoraiot.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.integradoraiot.ui_viewmodel.PersonaViewModel;

public class PersonaViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public PersonaViewModelFactory(Application application) {
        this.application = application; // Usamos el contexto de la aplicación
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PersonaViewModel.class)) {
            return (T) new PersonaViewModel(application);  // Pasamos el contexto de la aplicación
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
