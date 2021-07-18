package com.inacap.proveedores;
public class Usuario {
    int id;
    String nombre, apellido, usuario, contrasena;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String toString() {
        return "ID=" + id + '\n' +
                "Nombre='" + nombre + '\n' +
                "Apellido='" + apellido + '\n' +
                "Usuario='" + usuario + '\n' +
                "Contraseña='" + contrasena + '\n';
    }
}