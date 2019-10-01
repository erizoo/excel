package com.example.demo.controllers

import com.example.demo.models.TypeGroup
import com.example.demo.models.Product
import com.example.demo.service.ExcelService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
@RequestMapping("/api")
class ExcelController(private val excelService: ExcelService) {

    val counter = AtomicLong()

    @GetMapping("/excel")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Product {
        return  Product( "",0.0 , TypeGroup("dsgsdg", 1) )
    }

    @GetMapping("/excel/parse")
    fun parseExcel(@RequestParam(value = "name", defaultValue = "World") name: String) =
            excelService.parseExcel()
}