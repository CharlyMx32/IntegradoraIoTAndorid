package com.example.integradoraiot.models;

public class KidRequest2 {
    private String nombre_kid;
    private String apellido_paterno_kid;

    public KidRequest2(String nombre_kid, String apellido_paterno_kid) {
        this.nombre_kid = nombre_kid;
        this.apellido_paterno_kid = apellido_paterno_kid;
    }

    public String getNombre_kid() {
        return nombre_kid;
    }

    public String getApellido_paterno_kid() {
        return apellido_paterno_kid;
    }
}