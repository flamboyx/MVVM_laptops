package com.example.mvvmlaptops.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Laptops (
    val model: String,
    val manufacturer: String,
    val cpu: String,
    val cpuManufacturer: String,
    val ram: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
):java.io.Serializable