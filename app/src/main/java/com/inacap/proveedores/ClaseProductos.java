package com.inacap.proveedores;

public class ClaseProductos {
    int idProducto, stockProducto, precioProducto;
    String categoriaProducto, nombreProducto;

    public ClaseProductos(){
    }
    public ClaseProductos(int idProducto, String categoriaProducto, String nombreProducto, int precioProducto, int stockProducto){
        this.idProducto = idProducto;
        this.categoriaProducto = categoriaProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
    }

    public int getIdProducto(){
        return idProducto;
    }
    public void setIdProducto(int idProducto){
        this.idProducto = idProducto;
    }

    public String getCategoriaProducto(){
        return categoriaProducto;
    }
    public void setCategoriaProducto(String categoriaProducto){
        this.categoriaProducto = categoriaProducto;
    }

    public String getNombreProducto(){
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public int getPrecioProducto(){
        return precioProducto;
    }
    public void setPrecioProductoProducto(int precioProducto){
        this.precioProducto = precioProducto;
    }

    public int getStockProducto(){
        return stockProducto;
    }
    public void setStockProducto(int stockProducto){
        this.stockProducto = stockProducto;
    }

    //@NonNull
    @Override
    public String toString() {
        return "ID del Producto=" + idProducto + '\n' +
                "Categoria del Producto=" + categoriaProducto + '\n' +
                "Nombre del Producto=" + nombreProducto + '\n' +
                "Precio del Producto=" + precioProducto + '\n' +
                "Stock del Producto=" + stockProducto + '\n';
    }

}
