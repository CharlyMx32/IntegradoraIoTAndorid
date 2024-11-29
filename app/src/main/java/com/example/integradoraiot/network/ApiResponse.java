package com.example.integradoraiot.network;

import java.util.List;
import com.example.integradoraiot.models.modelo_kids;

public class ApiResponse {
    private Tutor tutor;
    private List<modelo_kids> niños;

    // Getters y setters
    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<modelo_kids> getNiños() {
        return niños;
    }

    public void setNiños(List<modelo_kids> niños) {
        this.niños = niños;
    }

    public String getStatus() {
        return "¡Operación exitosa!";
    }

    public String getMessage() {
        return "Operación NOOO exitosa";
    }

    public static class Tutor {
        private int id_tutor;
        private int id_persona;
        private String nombre;
        private String apellido_paterno;
        private String fotoPerfil;

        // Getters y setters
        public int getId_tutor() {
            return id_tutor;
        }

        public void setId_tutor(int id_tutor) {
            this.id_tutor = id_tutor;
        }

        public int getId_persona() {
            return id_persona;
        }

        public void setId_persona(int id_persona) {
            this.id_persona = id_persona;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido_paterno() {
            return apellido_paterno;
        }

        public void setApellido_paterno(String apellido_paterno) {
            this.apellido_paterno = apellido_paterno;
        }

        public String getFotoPerfil() {
            return fotoPerfil;
        }
    }
}
