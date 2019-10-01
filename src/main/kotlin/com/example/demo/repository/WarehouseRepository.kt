package com.example.demo.repository

import com.example.demo.models.Warehouse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WarehouseRepository : JpaRepository<Warehouse, Long> {

    @Query(value = "SELECT * FROM warehouse where id = :id", nativeQuery = true)
    fun findByIdCustom(@Param("id") id: Int): Warehouse
}