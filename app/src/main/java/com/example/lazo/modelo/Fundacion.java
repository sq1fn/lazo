package com.example.lazo.modelo;

public class Fundacion extends Usuario{
    private String direccion;
    private String categoria;

    // Constructor vacío necesario para Firebase
    public Fundacion() {}

    public Fundacion(String nombre, String correo, String contrasena, String telefono, String direccion, String categoria) {
        super(nombre, correo, contrasena, telefono);
        this.direccion = direccion;
        this.categoria = categoria;
    }

    // Getters y setters
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
