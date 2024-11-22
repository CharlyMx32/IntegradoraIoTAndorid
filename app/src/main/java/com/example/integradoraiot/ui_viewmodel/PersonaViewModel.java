package com.example.integradoraiot.ui_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.repository.PersonaRepository;

public class PersonaViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();
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

    public LiveData<Boolean> getLoginSuccess() { return loginSuccess; }

    public LiveData<String> getLoginError() { return loginError; }

    public void registerUser(String nombre, String apellido, String sexo, String fechaNacimiento, String correo, String contrasena) {
        RegisterRequest registerRequest = new RegisterRequest(correo, contrasena, nombre, apellido, sexo, fechaNacimiento);

        personaRepository.registerUser(registerRequest, new PersonaRepository.RegisterCallback() {
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

    public void loginUser(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        personaRepository.loginUser(request, new PersonaRepository.LoginCallback() {
            @Override
            public void onSuccess(String token) {
                guardarToken(token);
                loginSuccess.setValue(true);
            }

            @Override
            public void onError(String errorMessage) {
                loginError.setValue(errorMessage);
            }
        });
    }

    private void guardarToken(String token) {
        // Esta función debe implementarse en la actividad o adaptarse según el contexto
    }
}
