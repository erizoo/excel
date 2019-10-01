package com.example.demo.repository

import com.example.demo.models.TypeGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TypeGroupRepository : JpaRepository<TypeGroup, Long> {

    @Query(value = "SELECT * FROM type_group where name = :typeName", nativeQuery = true)
    fun findByName(@Param("typeName")typeName: String): List<TypeGroup>
}