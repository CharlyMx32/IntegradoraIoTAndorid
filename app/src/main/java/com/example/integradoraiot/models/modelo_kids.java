package com.example.integradoraiot.models;

// Modelo para el niño
public class modelo_kids {
    private int id_kid;
    private String nombre;
    private String apellido_paterno;
    private int edad;
    private String sexo;
    private String foto_perfil;

    // Getters y setters
    public int getId_kid() {
        return id_kid;
    }

    public void setId_kid(int id_kid) {
        this.id_kid = id_kid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }
}
