package com.example.demo.repository

import com.example.demo.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ExcelRepository : JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product where name = :typeName", nativeQuery = true)
    fun findByName(@Param("typeName") typeName: String?): List<Product>
}
