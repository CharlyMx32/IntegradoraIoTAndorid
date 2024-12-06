package com.example.integradoraiot.models;

// Modelo para el niño
public class modelo_kids {
    private int id_kid;
    private String nombre;
    private String apellido_paterno;
    private int edad;
    private String gameName; // Añadir campo gameName
    private String sexo;
    private String foto_perfil;

    // Constructor que ahora acepta gameName como parámetro
    public modelo_kids(String nombre, String apellido_paterno, int edad, String gameName) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.edad = edad;
        this.gameName = gameName; // Asignación de gameName
    }

    // Getters y setters
    public int getId_kid() {
        return id_kid;
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

    public int getEdad() {
        return edad;
    }

    public String getGameName() {
        return gameName; // Getter para gameName
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
