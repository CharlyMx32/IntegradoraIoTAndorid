package com.example.integradoraiot.models;

public class Game {
    private int id_juego;
    private String nombre;
    private String descripcion;
    private String imagen;

    public Game(int id_juego, String nombre, String descripcion, String imagen) {
        this.id_juego = id_juego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getId() {
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


