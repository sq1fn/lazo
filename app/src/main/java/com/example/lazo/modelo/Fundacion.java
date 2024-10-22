package com.example.lazo.modelo;

public class Fundacion extends Usuario {
    private String direccion;
    private String categoria;
    private String descripcion;

    public Fundacion() {super();}

    public Fundacion(String nombreUsuario, String correoUsuario, String contrasenaUsuario, String telefonoUsuario, String direccionF, String categoriaSeleccionada, String descripcion) {
        super(nombreUsuario, correoUsuario, contrasenaUsuario, telefonoUsuario);
        this.direccion = direccionF;
        this.categoria = categoriaSeleccionada;
        this.descripcion = descripcion;
    }

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getCategoria() {return categoria;}
    public void setCategoria(String categoria) {this.categoria = categoria;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
