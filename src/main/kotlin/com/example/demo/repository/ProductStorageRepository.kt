package com.example.demo.repository

import com.example.demo.models.ProductStorage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductStorageRepository : JpaRepository<ProductStorage, Long>