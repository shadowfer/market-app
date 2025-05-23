package com.tecdesoftware.market.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name="productos")

public class Producto {

    @Id // Es la llave primaria
    //Autogenera ids autoincrementables
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id_producto")
    private int idProducto;


    private String nombre;

    @Column(name="id_categoria")
    private Integer idCategoria;

    @Column(name="codigo_barras")
    private String codigoBarras;

    @Column(name="precio_venta")
    private Double precioVenta;

    @Column(name="cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
