package com.example.integradoraiot.network;

import java.util.List;

import com.example.integradoraiot.models.estadisticas;
import com.example.integradoraiot.models.modelo_kids;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private Tutor tutor;
    private List<modelo_kids> niños;

    @SerializedName("estadisticas")
    private estadisticas estadisticas;

    // Getters y setters
    public Tutor getTutor() {
        return tutor;
    }

    public List<modelo_kids> getNiños() {
        return niños;
    }

    public String getStatus() {
        return "¡Operación exitosa!";
    }

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    private String mensaje;
    private String estado;
    private String tiempoJugado;
    private String tiempoTranscurrido;

    // Getter y Setter para mensaje
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    // Getter y Setter para estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Getter y Setter para tiempoJugado
    public String getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(String tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }

    // Getter y Setter para tiempoTranscurrido
    public String getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    private List<Estadistica> estadistica;

    public List<Estadistica> getEstadistica() {
        return estadistica;
    }


    public void setTiempoTranscurrido(String tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    public static class Tutor {
        private int id_tutor;
        private int id_persona;
        private String nombre;
        private String apellido_paterno;
        private String fotoPerfil;

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getFotoPerfil() {
            return fotoPerfil;
        }

        private List<Estadistica> estadisticas;

        public List<Estadistica> getEstadisticas() {
            return estadisticas;
        }

        public void setEstadisticas(List<Estadistica> estadisticas) {
            this.estadisticas = estadisticas;
        }


    }
    public static class Estadistica {
        private String nombre_juego;
        private String total_tiempo_jugado;
        private int numero_partidas;

        public String getNombre_juego() {
            return nombre_juego;
        }

        public void setNombre_juego(String nombre_juego) {
            this.nombre_juego = nombre_juego;
        }

        public String getTotal_tiempo_jugado() {
            return total_tiempo_jugado;
        }

        public void setTotal_tiempo_jugado(String total_tiempo_jugado) {
            this.total_tiempo_jugado = total_tiempo_jugado;
        }

        public int getNumero_partidas() {
            return numero_partidas;
        }


    }

}

