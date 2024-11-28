package com.example.integradoraiot.models;

public class Game {
    private String nombre;
    private int id;
    private String descripcion;

    public Game(String name, int id, String descripcion) {
        this.nombre = name;
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getName() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

}

