package com.example.demo.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.io.Serializable
import javax.validation.constraints.NotBlank

@Entity
data class Product(

        @get: NotBlank
        val name: String = "",

        @get: NotBlank
        val price: Int = 0,

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0

) : Serializable{
        private constructor() : this("", 0)
}

