package com.example.demo.controllers

import com.example.demo.models.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class ExcelController {

    val counter = AtomicLong()

    @GetMapping("/excel")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
            Product(counter.incrementAndGet(), "Hello, $name")
}