package com.inacap.proveedores;

import androidx.annotation.NonNull;

public class ClasePedidos {
    int idPedido, cantidadProducto;
    String nombreCliente, ciudadPedido, nombreProducto, estadoPedido, fechaCreacion, fechaEntrega;

    public ClasePedidos(){
    }
    public ClasePedidos(int idPedido, String nombreCliente, String ciudadPedido, String nombreProducto, int cantidadProducto, String estadoPedido, String fechaCreacion, String fechaEntrega){
        this.idPedido = idPedido;
        this.nombreCliente = nombreCliente;
        this.ciudadPedido = ciudadPedido;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.estadoPedido = estadoPedido;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntrega = fechaEntrega;
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
        return nombreCliente;
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

    //@NonNull
    @Override
    public String toString() {
        return "ID=" + idPedido + '\n' +
                "Nombre Cliente=" + nombreCliente + '\n' +
                "CiudadPedido=" + ciudadPedido + '\n' +
                "Nombre Producto=" + nombreProducto + '\n' +
                "Cantidad Producto=" + cantidadProducto + '\n' +
                "Estado Pedido=" + estadoPedido + '\n' +
                "Fecha Creación=" + fechaCreacion + '\n' +
                "Fecha Entrega=" + fechaEntrega + '\n';
    }
}
