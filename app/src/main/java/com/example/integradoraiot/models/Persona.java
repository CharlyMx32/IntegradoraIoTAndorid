package com.example.integradoraiot.models;

public class Persona {
    private String email;
    private String apellido;
    private String sexo;
    private String fechaNacimiento;
    private String password;
    private String nombre;

    public Persona(String email, String password, String nombre, String apellido, String sexo, String fechaNacimiento) {
        this.email = email;
        this.apellido = apellido;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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