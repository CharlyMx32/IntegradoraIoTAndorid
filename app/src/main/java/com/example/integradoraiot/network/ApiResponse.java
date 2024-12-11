package com.example.integradoraiot.network;

import java.util.List;
import com.example.integradoraiot.models.modelo_kids;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private Tutor tutor;
    private List<modelo_kids> niños;

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
    private String tiempoJugado;  // Para el tiempo total cuando la partida finaliza
    private String tiempoTranscurrido;  // Para el tiempo transcurrido cuando la partida está activa

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

    public void setTiempoTranscurrido(String tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
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
    }
}
