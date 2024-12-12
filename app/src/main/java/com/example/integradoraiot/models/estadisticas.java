package com.example.integradoraiot.models;
import java.io.Serializable;

public class estadisticas implements Serializable {
    private String nivel_actual;
    private String mejor_puntuacion;
    private String tiempo_jugado;

    // Getters y setters
    public String getNivelActual() {
        return nivel_actual;
    }

    public void setNivelActual(String nivel_actual) {
        this.nivel_actual = nivel_actual;
    }

    public String getMejorPuntuacion() {
        return mejor_puntuacion;
    }

    public void setMejorPuntuacion(String mejor_puntuacion) {
        this.mejor_puntuacion = mejor_puntuacion;
    }

    public String getTiempoJugado() {
        return tiempo_jugado;
    }

    public void setTiempoJugado(String tiempo_jugado) {
        this.tiempo_jugado = tiempo_jugado;
    }
}
