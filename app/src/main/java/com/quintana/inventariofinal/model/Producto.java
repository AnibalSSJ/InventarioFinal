package com.quintana.inventariofinal.model;

public class Producto {

    String cantidadProducto, nombreProducto, precioProductoConjunto, precioProductoUnidad, spinnerTipo;

    public Producto(){}

    public Producto(String cantidadProducto, String nombreProducto, String precioProductoConjunto, String precioProductoUnidad, String spinnerTipo) {
        this.cantidadProducto = cantidadProducto;
        this.nombreProducto = nombreProducto;
        this.precioProductoConjunto = precioProductoConjunto;
        this.precioProductoUnidad = precioProductoUnidad;
        this.spinnerTipo = spinnerTipo;


    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioProductoConjunto() {
        return precioProductoConjunto;
    }

    public void setPrecioProductoConjunto(String precioProductoConjunto) {
        this.precioProductoConjunto = precioProductoConjunto;
    }

    public String getPrecioProductoUnidad() {
        return precioProductoUnidad;
    }

    public void setPrecioProductoUnidad(String precioProductoUnidad) {
        this.precioProductoUnidad = precioProductoUnidad;
    }

    public String getSpinnerTipo() {
        return spinnerTipo;
    }

    public void setSpinnerTipo(String spinnerTipo) {
        this.spinnerTipo = spinnerTipo;
    }



}
