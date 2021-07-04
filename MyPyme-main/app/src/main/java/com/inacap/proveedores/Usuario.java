package com.inacap.proveedores;
public class Usuario {
    int id;
    String nombre, apellido, usuario, contraseña;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String usuario, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.contraseña = contraseña;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String toString() {
        return "ID=" + id + '\n' +
                "Nombre='" + nombre + '\n' +
                "Apellido='" + apellido + '\n' +
                "Usuario='" + usuario + '\n' +
                "Contraseña='" + contraseña + '\n';
    }
}