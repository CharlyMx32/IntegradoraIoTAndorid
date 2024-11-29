package com.example.integradoraiot.models;

public class KidRequest {
    private String id_persona;
    private String nombre_kid;
    private String apellido_paterno_kid;
    private int edad_kid;
    private String genero_kid;

    // Constructor
    public KidRequest(String id_persona, String nombre_kid, String apellido_paterno_kid, int edad_kid, String genero_kid) {
        this.id_persona = id_persona;
        this.nombre_kid = nombre_kid;
        this.apellido_paterno_kid = apellido_paterno_kid;
        this.edad_kid = edad_kid;
        this.genero_kid = genero_kid;
    }

    // Getters y Setters
    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_kid() {
        return nombre_kid;
    }

    public void setNombre_kid(String nombre_kid) {
        this.nombre_kid = nombre_kid;
    }

    public String getApellido_paterno_kid() {
        return apellido_paterno_kid;
    }

    public void setApellido_paterno_kid(String apellido_paterno_kid) {
        this.apellido_paterno_kid = apellido_paterno_kid;
    }

    public int getEdad_kid() {
        return edad_kid;
    }

    public void setEdad_kid(int edad_kid) {
        this.edad_kid = edad_kid;
    }

    public String getGenero_kid() {
        return genero_kid;
    }

    public void setGenero_kid(String genero_kid) {
        this.genero_kid = genero_kid;
    }
}
