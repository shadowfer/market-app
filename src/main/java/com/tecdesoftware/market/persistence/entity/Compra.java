package com.tecdesoftware.market.persistence.entity;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="compras")
public class Compra {

    @Id // Es la llave primaria
    //Autogenera ids autoincrementables
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name="id_compra")
    private int idCompra;

    @Column(name="id_cliente")
    private String idCliente;


    private LocalDateTime fecha;

    @Column(name="medio_pago")
    private String medioPago;


    private String comentario;

    private String estado;

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
