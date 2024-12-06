package com.example.integradoraiot.models;

public class Game {
    private String id_juego;
    private String nombre;
    private String descripcion;
    private String imagen;

    // Constructor
    public Game(String id_juego, String nombre, String descripcion, String imagen) {
        this.id_juego = id_juego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getId_juego() { // Cambiado a getId_juego
        return id_juego;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }
}



