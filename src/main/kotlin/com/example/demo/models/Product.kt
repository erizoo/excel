package com.example.demo.models

import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Product(

        @get: NotBlank
        val name: String = "",

        val price: Double = 0.0,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        val typeGroupId: TypeGroup,

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0

) : Serializable
