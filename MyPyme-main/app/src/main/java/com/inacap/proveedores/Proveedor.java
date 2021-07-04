package com.inacap.proveedores;

/**
 *  Aplicación de la clase Proveedor referenciada en el diagrama de Clases.
 */

public class Proveedor {
    /**
     *  Atributos de Clase
     */
    int id_proveedor;
    String nombre, apellido, correo, numero;
    /**
     *  Constructor Vacío.
     */
    public Proveedor() {
    }
    /**
     *  Constructor Completo.
     */
    public Proveedor(int id_proveedor, String nombre, String apellido, String correo, String numero) {
        this.id_proveedor = id_proveedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.numero = numero;
    }
    /**
     *  Getters y Setters.
     */
    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}


