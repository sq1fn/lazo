package com.example.lazo.modelo;

public class Usuario {
    private String nombre;
    private String correo;
    private String contrasena;
    private String telefono;
    private String fotoperfil;

    public Usuario(){}

    public Usuario(String nombre, String correo, String contrasena, String telefono, String fotoperfil){
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fotoperfil = fotoperfil;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFotoperfil() { return fotoperfil; }
    public void setFotoperfil(String fotoperfil) { this.fotoperfil = fotoperfil; }
}
