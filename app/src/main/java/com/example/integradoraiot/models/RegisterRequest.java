package com.example.integradoraiot.models;

public class RegisterRequest {
    private String email;
    private String password;
    private String nombre;

    public RegisterRequest(String email, String password, String nombre) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }

    // Getters y Setters (opcional si no usas Gson directamente)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = nombre;
    }
}