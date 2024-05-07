package com.example.mvvmlaptops.data

import com.example.mvvmlaptops.DI.Dep
import com.example.mvvmlaptops.domain.DataGenerator
import com.example.mvvmlaptops.domain.IRepository
import com.example.mvvmlaptops.domain.LaptopModel

class Repository : IRepository {
    override suspend fun add(model: LaptopModel) {
        val laptop = Laptops(model.model, model.manufacturer, model.cpu, model.cpuManufacturer, model.ram)
        Dep.db.getLaptopsDao().insert(laptop)
    }

    override suspend fun clearTable() {
        Dep.db.getLaptopsDao().clearTable()
    }

    override suspend fun getAllLaptops(): MutableList<LaptopModel> {
        val list = Dep.db.getLaptopsDao().getAllLaptops()
        var newList = mutableListOf<LaptopModel>()
        for (elem in list) {
            newList.add(LaptopModel(elem.model, elem.manufacturer, elem.cpu, elem.cpuManufacturer, elem.ram))
        }

        return newList
    }

    override suspend fun generateTestData() {
        val generator = DataGenerator()
        for (i in 1..20) {
            val laptop = generator.generate()
            add(laptop)
        }
    }
}