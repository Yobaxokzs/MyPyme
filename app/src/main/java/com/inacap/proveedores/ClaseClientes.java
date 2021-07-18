package com.inacap.proveedores;

public class ClaseClientes {
    int idCliente, numeroCliente;
    String nombreCliente, apellidoCliente, correoCliente, ciudadCliente;

    public ClaseClientes(){
    }
    public ClaseClientes(int idCliente, String nombreCliente, String apellidoCliente, int numeroCliente, String correoCliente, String ciudadCliente){
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.numeroCliente = numeroCliente;
        this.correoCliente = correoCliente;
        this.ciudadCliente = ciudadCliente;
    }

    public int getIdCliente(){
        return idCliente;
    }
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente(){
        return apellidoCliente;
    }
    public void setApellidoCliente(String apellidoCliente){
        this.apellidoCliente = apellidoCliente;
    }

    public int getNumeroCliente(){
        return numeroCliente;
    }
    public void setNumeroCliente(int numeroCliente){
        this.numeroCliente = numeroCliente;
    }

    public String getCorreoCliente(){
        return correoCliente;
    }
    public void setCorreoCliente(String correoCliente){
        this.correoCliente = correoCliente;
    }

    public String getCiudadCliente(){
        return ciudadCliente;
    }
    public void setCiudadCliente(String ciudadCliente){
        this.ciudadCliente = ciudadCliente;
    }

    //@NonNull
    @Override
    public String toString() {
        return "ID=" + idCliente + '\n' +
                "Nombre Cliente=" + nombreCliente + '\n' +
                "Apellido Cliente=" + apellidoCliente + '\n' +
                "Numero Cliente=" + numeroCliente + '\n' +
                "Correo Cliente=" + correoCliente + '\n' +
                "Ciudad Cliente=" + ciudadCliente + '\n';
    }
}
