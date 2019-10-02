package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class ProductStorage(

        val amount: Int = 0,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
        val productId: Product,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
        val warehouseId: Warehouse,

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0

) : Serializable