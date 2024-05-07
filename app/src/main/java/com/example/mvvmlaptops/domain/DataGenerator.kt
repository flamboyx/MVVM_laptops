package com.example.mvvmlaptops.domain

class DataGenerator {
    private val models = listOf(
        "HONOR MagicBook 14",
        "MSI Katana 17",
        "GIGABYTE G5 MF",
        "ASUS TUF Gaming A15",
        "MSI Bravo 15",
        "ASUS VivoBook Pro 15",
        "DEXP Atlas",
        "HUAWEI MateBook D 15",
        "MSI Modern 15",
        "HONOR MagicBook 15",
        "Realme Book QHD"
    )

    private val cpus = listOf(
        "AMD Ryzen 5 5500U",
        "Intel Core i5-12450H",
        "Intel Core i5-12500H",
        "AMD Ryzen 7 7735HS",
        "AMD Ryzen 5 7535HS",
        "Intel Core i3-1215U",
        "Intel Core i5-1155G7",
        "Intel Core i5-1135G7",
        "Intel Core i7-12650H"
    )

    private val rams = listOf(
        4,
        8,
        16,
        32,
        64,
    )

    public fun generate(): LaptopModel {
        val model = models.random()
        val manufacturer = model.split(" ")[0]
        val cpu = cpus.random()
        val cpuManufacturer = cpu.split(" ")[0]
        val ram = rams.random()

        return LaptopModel (model, manufacturer, cpu, cpuManufacturer, ram)
    }
}