package com.example.integradoraiot.ui_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.models.Persona;
import com.example.integradoraiot.repository.PersonaRepository;

public class PersonaViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final PersonaRepository personaRepository;

    public PersonaViewModel() {
        personaRepository = new PersonaRepository();
    }

    public LiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void registerUser(String nombre, String apellido, String sexo, String fechaNacimiento, String correo, String contrasena) {
        Persona persona = new Persona(correo, contrasena, nombre, apellido, sexo, fechaNacimiento);

        personaRepository.registerUser(persona, new PersonaRepository.RegisterCallback() {
            @Override
            public void onSuccess() {
                isSuccess.setValue(true);
            }

            @Override
            public void onError(String message) {
                errorMessage.setValue(message);
            }
        });
    }
}
