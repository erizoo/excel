package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonBackReference
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class ProductStorage(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @MapsId("productId")
        @get: NotBlank
        val productId: Product,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @MapsId("warehouseId")
        @get: NotBlank
        val warehouseId: Warehouse,

        @get: NotBlank
        val amount: Int = 0

) : Serializable