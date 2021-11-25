package com.car105.yendondeesta.model;

public class Persona {
    private String id;
    private String Nombre;
    private String Celular;


    public Persona() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    @Override
    public String toString() {
        return Celular;
    }
}
