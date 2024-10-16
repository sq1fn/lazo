package com.example.lazo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String nombre;
    private List<String> categorias;

    public Producto(String nombre) {
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
    }

    public Producto(String nombre, List<String> categorias) {
        this.nombre = nombre;
        this.categorias = categorias;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
}
