package com.example.integradoraiot.models;

public class Descripcion {
    private String name;
    private String description;
    private String imageUrl; // URL de la imagen proporcionada por la API
    private int gameId; // ID del juego correspondiente

    // Getters y Setters
    public String getName() {
        return name;
    }

    public int getGameId() {
        return gameId;
    }


    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

