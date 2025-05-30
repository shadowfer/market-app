package com.tecdesoftware.market.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "clientes")
class Cliente {
    // Getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var nombre: String? = null

    var apellidos: String? = null

    @Column(name = "telefono")
    var telefono: Long? = null

    var direccion: String? = null

    @Column(name = "correo_electronico")
    var correo: String? = null
}