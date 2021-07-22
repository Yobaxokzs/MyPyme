package com.inacap.proveedores;

public class ClasePp {
    int idPp, precioPp, cantidadPp;
    String nombrePp;
    public ClasePp(int idPp, int precioPp, int cantidadPp, String nombrePp) {
        this.idPp = idPp;
        this.precioPp = precioPp;
        this.cantidadPp = cantidadPp;
        this.nombrePp = nombrePp;
    }
    public ClasePp() {
    }
    public int getIdPp() {
        return idPp;
    }
    public void setIdPp(int idPp) {
        this.idPp = idPp;
    }
    public int getPrecioPp() {
        return precioPp;
    }
    public void setPrecioPp(int precioPp) {
        this.precioPp = precioPp;
    }
    public int getCantidadPp() {
        return cantidadPp;
    }
    public void setCantidadPp(int cantidadPp) {
        this.cantidadPp = cantidadPp;
    }
    public String getNombrePp() {
        return nombrePp;
    }
    public void setNombrePp(String nombrePp) {
        this.nombrePp = nombrePp;
    }
    //@NonNull
    @Override
    public String toString() {
        return "ID=" + idPp + '\n' +
                "Precio=" + precioPp + '\n' +
                "Cantidad=" + cantidadPp + '\n' +
                "Nombre=" + nombrePp + '\n';
    }
}