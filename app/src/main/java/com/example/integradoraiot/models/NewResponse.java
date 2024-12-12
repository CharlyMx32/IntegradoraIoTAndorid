package com.example.integradoraiot.models;

import java.util.List;

public class NewResponse{
    private List<Estadistica> estadisticas;

    public List<Estadistica> getEstadisticas() {
        return estadisticas;
    }

    public static class Estadistica {
        private String nombre_juego;
        private String total_tiempo_jugado;
        private int numero_partidas;

        public String getNombre_juego() {
            return nombre_juego;
        }

        public String getTotal_tiempo_jugado() {
            return total_tiempo_jugado;
        }

        public int getNumero_partidas() {
            return numero_partidas;
        }
    }
}
