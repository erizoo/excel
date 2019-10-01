package com.example.demo.service

import com.example.demo.models.Product
import com.example.demo.models.ProductStorage
import com.example.demo.models.TypeGroup
import com.example.demo.models.Warehouse
import com.example.demo.repository.ExcelRepository
import com.example.demo.repository.ProductStorageRepository
import com.example.demo.repository.TypeGroupRepository
import com.example.demo.repository.WarehouseRepository
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExcelService(
        private val excelRepository: ExcelRepository,
        private val typeGroupRepository: TypeGroupRepository,
        private val warehouseRepository: WarehouseRepository,
        private val productStorageRepository: ProductStorageRepository
) {

    private var typeName = ""
    private var typeCount = 0
    private var typeList: MutableList<TypeGroup> = mutableListOf()
    private var productList: MutableList<Product> = mutableListOf()
    private var warehouseList: MutableList<Warehouse> = mutableListOf()
    private var productStorageList: MutableList<ProductStorage> = mutableListOf()

    fun parseExcel(): Any? {
        val classloader = Thread.currentThread().contextClassLoader
        val inputStream = classloader.getResourceAsStream("test.xlsx")

        // Get the workbook instance for XLS file
        val workbook = XSSFWorkbook(inputStream)

        // Get first sheet from the workbook
        val sheet = workbook.getSheetAt(0)

        warehouseRepository.deleteAll()
        typeGroupRepository.deleteAll()
        excelRepository.deleteAll()
        productStorageRepository.deleteAll()
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(3).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(4).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(5).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(6).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(7).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(8).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(9).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(10).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(11).stringCellValue))
        warehouseList.add(Warehouse(sheet.getRow(0).getCell(12).stringCellValue))

        for (row in sheet.rowIterator()) {
            try {
                val book = row.sheet.workbook
                val style = row.getCell(3).cellStyle
                val fontIndex = style.fontIndex.toInt()
                val font = book.getFontAt(fontIndex.toShort())
                if (font.bold) {
                    ++typeCount
                    typeName = row.getCell(0).stringCellValue
                    typeList.add(TypeGroup(typeName, typeCount.toLong()))
                } else {
                    val product = Product(row.getCell(0).stringCellValue, row.getCell(13).numericCellValue, TypeGroup(typeName, typeCount.toLong()))
                    productList.add(product)
                    productStorageList.add(ProductStorage(row.getCell(3).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(4).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(5).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(6).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(7).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(8).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(9).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(10).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(11).numericCellValue.toInt(), product, warehouseList[0]))
                    productStorageList.add(ProductStorage(row.getCell(12).numericCellValue.toInt(), product, warehouseList[0]))
                }
            } catch (e: IllegalStateException) {
                typeCount = 0
                try {
                    println(row.getCell(3).stringCellValue.toString() + " " + row.getCell(13).numericCellValue)
                } catch (e: IllegalStateException) {
                }
            }

        }

        saveWarehouse(warehouseList)
        saveTypeGroup(typeList)
        saveProducts(productList)
        saveProductStorage(productStorageList)
        print(sheet.sheetName)
        return null
    }

    @Transactional
    fun saveProducts(productList: MutableList<Product>) {
        excelRepository.saveAll(productList)
    }

    @Transactional
    fun saveTypeGroup(typeList: MutableList<TypeGroup>) {
        typeGroupRepository.saveAll(typeList)
    }

    @Transactional
    fun saveWarehouse(warehouseList: MutableList<Warehouse>) {
        warehouseRepository.saveAll(warehouseList)
    }

    @Transactional
    fun saveProductStorage(productStorageList: MutableList<ProductStorage>) {
        productStorageRepository.saveAll(productStorageList)
    }


}