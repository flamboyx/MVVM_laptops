package com.example.mvvmlaptops.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmlaptops.DI.Dep
import com.example.mvvmlaptops.domain.LaptopModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LaptopViewModel : ViewModel() {
    val manufacturerColor = MutableLiveData<String>(chosen)
    val cpuColor = MutableLiveData<String>(default)
    val ramColor = MutableLiveData<String>(default)
    var filter = MANUFACTURER
    val newList = MutableLiveData<MutableList<LaptopModel>>()
    private val originalList = MutableLiveData<List<LaptopModel>>()

    fun filterManufacturer() {
        filter = MANUFACTURER
        manufacturerColor.value = chosen
        cpuColor.value = default
        ramColor.value = default
    }

    fun filterCpu() {
        filter = CPU
        manufacturerColor.value = default
        cpuColor.value = chosen
        ramColor.value = default
    }

    fun filterRam() {
        filter = RAM
        manufacturerColor.value = default
        cpuColor.value = default
        ramColor.value = chosen
    }

    fun sort(filter: Int) {
        when (filter) {
            MANUFACTURER -> {
                newList.value = (newList.value?.sortedBy { it.manufacturer })?.toMutableList()
            }
            CPU -> {
                newList.value = (newList.value?.sortedBy { it.cpu })?.toMutableList()
            }
            RAM -> {
                newList.value = (newList.value?.sortedBy { it.ram })?.toMutableList()
            }
        }
    }

    fun search(text: String) {
        newList.value = (originalList.value?.filter { it.model.contains(text, ignoreCase = true) })?.toMutableList()
    }

    fun add(laptop: LaptopModel) {
        viewModelScope.launch {
            async { Dep.getRepository().add(laptop) }.await()
        }
    }

    fun getAllLaptops() {
        viewModelScope.launch {
            originalList.value = async { Dep.getRepository().getAllLaptops() }.await()
            newList.value = async { Dep.getRepository().getAllLaptops() }.await()
        }
    }

    fun clear() {
        viewModelScope.launch {
            async { Dep.getRepository().clearTable() }.await()
        }
    }

    fun generateTestData() {
        viewModelScope.launch {
            Dep.getRepository().generateTestData()
        }
    }

    companion object Constants {
        private const val chosen = "#415a77"
        private const val default = "#1b263b"
        private const val MANUFACTURER = 0
        private const val CPU = 1
        private const val RAM = 2
    }
}