package com.example.mvvmlaptops
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class LaptopViewModel : ViewModel() {
    private val laptopList = MutableLiveData<MutableList<Laptop>>()
    private val laptopFilteredList = MutableLiveData<MutableList<Laptop>>()
    val laptops: MutableLiveData<MutableList<Laptop>> get() = laptopFilteredList

    init {
        val model = listOf(
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
        val cpu = listOf(
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
        val ram = listOf(
            "4 GB",
            "8 GB",
            "16 GB",
            "32 GB",
            "64 GB",
        )

        laptopList.value = MutableList(50) {
            val randomModel = model[Random.nextInt(model.size)]
            val randomCpu = cpu[Random.nextInt(cpu.size)]
            val randomRam = ram[Random.nextInt(ram.size)]
            Laptop(
                model = randomModel,
                cpu = randomCpu,
                ram = randomRam
            )
        }
        laptopFilteredList.value = laptopList.value
    }

    fun filter(text: String) {
        if (text.isEmpty()) {
            laptopFilteredList.value = laptopList.value
        } else {
            val filteredList =
                laptopList.value?.filter { it.model.contains(text, ignoreCase = true) }?.toMutableList()
            laptopFilteredList.value = filteredList
            Log.d("LaptopViewModel", "Filtered list size: ${filteredList?.size}")
        }
    }
}