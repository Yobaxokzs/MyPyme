package com.inacap.proveedores;

import androidx.annotation.NonNull;

public class ClasePedidos {
    int idPedido, cantidadProducto;
    String nombreCliente, ciudadPedido, nombreProducto, estadoPedido, fechaCreacion, fechaEntrega, contactoPedido, precioPedido;

    public ClasePedidos(){
    }
    public ClasePedidos(int idPedido, String nombreCliente, String ciudadPedido, String nombreProducto, int cantidadProducto, String estadoPedido, String fechaCreacion, String fechaEntrega, String contactoPedido, String precioPedido){
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.ciudadPedido = ciudadPedido;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.estadoPedido = estadoPedido;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
        this.contactoPedido = contactoPedido;
        this.precioPedido = precioPedido;
    }

    public int getIdPedido(){
        return idPedido;
    }
    public void setIdPedido(int idPedido){
        this.idPedido = idPedido;
    }

    public String getNombreCliente(){
        return nombreCliente;
    }
    public void setNombreCliente(String nombreCliente){
        this.nombreCliente = nombreCliente;
    }

    public String getCiudadPedido(){
        return ciudadPedido;
    }
    public void setCiudadPedido(String ciudadPedido){
        this.ciudadPedido = ciudadPedido;
    }

    public String getNombreProducto(){
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto(){
        return cantidadProducto;
    }
    public void setCantidadProducto(int cantidadProducto){
        this.cantidadProducto = cantidadProducto;
    }

    public String getEstadoPedido(){
        return estadoPedido;
    }
    public void setEstadoPedido(String estadoPedido){
        this.estadoPedido = estadoPedido;
    }

    public String getFechaCreacion(){
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion){
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEntrega(){
        return fechaEntrega;
    }
    public void setFechaEntrega(String fechaEntrega){
        this.fechaEntrega = fechaEntrega;
    }

    public String getContactoPedido(){
        return contactoPedido;
    }
    public void setContactoPedido(String contactoPedido){
        this.contactoPedido = contactoPedido;
    }

    public String getPrecioPedido(){
        return precioPedido;
    }
    public void setPrecioPedido(String precioPedido){
        this.precioPedido = precioPedido;
    }
    //@NonNull
    @Override
    public String toString() {
        return "Código de Pedido:  " + idPedido + '\n' +
                "Nombre Cliente: " + nombreCliente + '\n' +
                "Ciudad de Pedido: " + ciudadPedido + '\n' +
                "Nombre de Producto: " + nombreProducto + '\n' +
                "Cantidad Producto: " + cantidadProducto + '\n' +
                "Estado Pedido: " + estadoPedido + '\n' +
                "Fecha Creación: " + fechaCreacion + '\n' +
                "Fecha Entrega: " + fechaEntrega + '\n' +
                "PRECIO : " + precioPedido + '\n';
    }
}
