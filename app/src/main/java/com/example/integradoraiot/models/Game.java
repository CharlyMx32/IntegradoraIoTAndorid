package com.example.integradoraiot.models;

public class Game {
    private String nombre;
    private int img;
    private String descripcion;

    public Game(String name, int img, String descripcion) {
        this.nombre = name;
        this.img = img;
        this.descripcion = descripcion;
    }

    public String getName() {
        return nombre;
    }

    public int getImg() {
        return img;
    }

    public String getDescription() {
        return descripcion;
    }

}

