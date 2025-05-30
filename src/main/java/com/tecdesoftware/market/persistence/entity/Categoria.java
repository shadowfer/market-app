package com.tecdesoftware.market.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Integer idCategoria;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    private String descripcion;
    private Boolean estado;

    public Integer getIdCategoria() {
        return idCategoria;
    }
}
