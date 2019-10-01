package com.example.demo.models

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class TypeGroup(

        @get: NotBlank
        val name: String = "",

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0

) : Serializable