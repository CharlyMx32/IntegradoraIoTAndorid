package com.example.integradoraiot.models;

public class Estadistica {
    private String nombre_juego;
    private String total_tiempo_jugado;
    private int numero_partidas;

    public String getNombreJuego() {
        return nombre_juego;
    }

    public String getTotalTiempoJugado() {
        return total_tiempo_jugado;
    }

    public int getNumeroPartidas() {
        return numero_partidas;
    }
}