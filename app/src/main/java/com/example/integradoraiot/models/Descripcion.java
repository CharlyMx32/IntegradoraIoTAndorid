package com.example.integradoraiot.models;

import java.util.Objects;

public class Descripcion {
    private int id;
    private String titulo;
    private String descripcion;

    // Constructor
    public Descripcion(int id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Sobrescribir equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Son el mismo objeto
        if (o == null || getClass() != o.getClass()) return false; // No es de la misma clase
        Descripcion that = (Descripcion) o;
        return id == that.id && // Comparar el id
                Objects.equals(titulo, that.titulo) && // Comparar el título
                Objects.equals(descripcion, that.descripcion); // Comparar la descripción
    }

    // Sobrescribir hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descripcion);
    }
}
