package com.example.navalbattlefinal.model;

public class Boats {
    protected int tamaño;
    protected String nombre;

    public Boats(int tamaño, String nombre) {
        this.tamaño = tamaño;
        this.nombre = nombre;
    }
    public int getTamaño() {
        return tamaño;
    }
    public String getNombre() {
        return nombre;
    }
}
