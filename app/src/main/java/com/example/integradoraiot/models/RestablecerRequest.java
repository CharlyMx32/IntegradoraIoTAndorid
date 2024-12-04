package com.example.integradoraiot.models;

public class RestablecerRequest {
    private String email;

    public RestablecerRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
