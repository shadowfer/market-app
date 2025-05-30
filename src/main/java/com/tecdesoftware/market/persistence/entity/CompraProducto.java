package com.tecdesoftware.market.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name="compras_productos")

public class CompraProducto {

    @EmbeddedId
    private CompraProductoPK id;

    private Integer cantidad;

    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    private Double total;
    private Boolean estado;
}
