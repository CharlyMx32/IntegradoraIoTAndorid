package com.example.integradoraiot.models;

public class Game {
    private String nombre;
    private String descripcion;

    public Game(String name, String descripcion) {
        this.nombre = name;
        this.descripcion = descripcion;
    }

    public String getName() {
        return nombre;
    }

    public String getDescription() {
        return descripcion;
    }
}

