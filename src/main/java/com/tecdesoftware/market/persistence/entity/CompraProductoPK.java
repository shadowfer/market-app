package com.tecdesoftware.market.persistence.entity;


//Para unir las 2 llaves y crear una llave compuesta

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

//Serializable es para que sea autoincrementable
@Embeddable
public class CompraProductoPK {

    @Column(name="id_compra")
    private int idCompra;

    public int getIdCompra() {
        return idCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    @Column(name="id_producto")
    private int idProducto;
}
