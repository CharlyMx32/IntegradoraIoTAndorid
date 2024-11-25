package com.example.integradoraiot.models;

public class modelo_kids {
    private String nombre;
    private String apellido_paterno;
    private String edad;
    private String sexo;

    public modelo_kids(String nombre, String apellido_paterno, String edad, String sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.apellido_paterno = apellido_paterno;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public String getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
