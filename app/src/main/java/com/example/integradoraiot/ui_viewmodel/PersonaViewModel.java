package com.example.integradoraiot.ui_viewmodel;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.integradoraiot.models.RegisterRequest;
import com.example.integradoraiot.models.LoginRequest;
import com.example.integradoraiot.repository.PersonaRepository;

import android.app.Application;
import android.content.Context;
import com.example.integradoraiot.TokenManager;


public class PersonaViewModel extends AndroidViewModel {
    private final MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();
    private final PersonaRepository personaRepository;
    private final TokenManager tokenManager;

    // Constructor que recibe Application
    public PersonaViewModel(Application application) {
        super(application); // Usamos el Application context
        personaRepository = new PersonaRepository(application);  // Usamos el contexto de la aplicación
        tokenManager = new TokenManager(application);  // Usamos el contexto de la aplicación
    }


    public LiveData<Boolean> getIsSuccess() {
        return isSuccess;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public LiveData<String> getLoginError() {
        return loginError;
    }

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
                guardarToken(token); // Guardar el token al iniciar sesión
                loginSuccess.setValue(true);
            }

            @Override
            public void onError(String errorMessage) {
                loginError.setValue(errorMessage);
            }
        });
    }

    private void guardarToken(String token) {
        tokenManager.saveToken(token); // Usar TokenManager para guardar el token
    }

    public String obtenerToken() {
        return tokenManager.getToken(); // Recuperar el token si es necesario
    }
}

