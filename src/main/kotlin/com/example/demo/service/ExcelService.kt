package com.example.demo.service

import com.example.demo.models.Product
import com.example.demo.repository.ExcelRepository
import org.springframework.stereotype.Service
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream



@Service
class ExcelService(private val excelRepository: ExcelRepository) {

    fun parseExcel(): Any? {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream = classloader.getResourceAsStream("test.xlsx")

        // Get the workbook instance for XLS file
        val workbook = XSSFWorkbook(inputStream)

        // Get first sheet from the workbook
        val sheet = workbook.getSheetAt(0)

        for (row in sheet.rowIterator()){
            try {
                println(row.getCell(0).stringCellValue + " " + row.getCell(12).numericCellValue)
                excelRepository.save(Product(row.getCell(0).stringCellValue, row.getCell(12).numericCellValue.toInt()))
            }catch (e: IllegalStateException){
                try {
                    println(row.getCell(0).numericCellValue.toString() + " " + row.getCell(12).numericCellValue)
                } catch (e: IllegalStateException){

                }

            }

        }
        print(sheet.sheetName)
        return null
    }

}