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

    @Query(value = "SELECT product.id AS productId, product.name AS productName, product.price AS productPrice, warehouse.name AS warehouseName, product_storage.amount AS productStorageAmount FROM product INNER JOIN product_storage ON product.id = product_storage.product_id_id " +
            "INNER JOIN warehouse ON product_storage.warehouse_id_id = warehouse.id WHERE product.name LIKE CONCAT('%', :keyword, '%') AND product_storage.amount > 0 LIMIT 10", nativeQuery = true)
    fun searchForName(@Param("keyword") keyword: String) : List<ResultDto>

    interface ResultDto {

        val warehouseName: String
        val productName: String
        val productPrice: Int
        val productStorageAmount: Int
        val productId: Long

    }
}
