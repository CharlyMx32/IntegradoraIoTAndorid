package com.example.integradoraiot.models;

public class UsuarioResponse {
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static class Usuario {
        private String email;
        private String nombre;
        private String apellido_paterno;
        private String apellido_materno;
        private String fecha_nacimiento;
        private String sexo;
        private String telefono;
        private String foto_perfil;

        // Getters y Setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getApellido_paterno() { return apellido_paterno; }
        public void setApellido_paterno(String apellido_paterno) { this.apellido_paterno = apellido_paterno; }

        public String getApellido_materno() { return apellido_materno; }
        public void setApellido_materno(String apellido_materno) { this.apellido_materno = apellido_materno; }

        public String getFecha_nacimiento() { return fecha_nacimiento; }
        public void setFecha_nacimiento(String fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

        public String getSexo() { return sexo; }
        public void setSexo(String sexo) { this.sexo = sexo; }

        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }

        public String getFoto_perfil() { return foto_perfil; }
        public void setFoto_perfil(String foto_perfil) { this.foto_perfil = foto_perfil; }
    }
}

