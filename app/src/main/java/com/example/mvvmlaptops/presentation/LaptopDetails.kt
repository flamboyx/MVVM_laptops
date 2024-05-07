package com.example.mvvmlaptops.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmlaptops.R
import com.example.mvvmlaptops.domain.LaptopModel

class LaptopDetails : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laptop_info)

        var laptopDetails: LaptopModel? = null
        if (intent.hasExtra(MainActivity.NEXT_SCREEN)) {
            laptopDetails = intent.getSerializableExtra(MainActivity.NEXT_SCREEN) as LaptopModel
        }

        if (laptopDetails != null) {
            findViewById<TextView>(R.id.laptopModel).text = "Модель: ${laptopDetails.model}"
            findViewById<TextView>(R.id.laptopManufacturer).text = "Производитель: ${laptopDetails.manufacturer}"
            findViewById<TextView>(R.id.laptopCpu).text = "ЦП: ${laptopDetails.cpu}"
            findViewById<TextView>(R.id.laptopCpuManufacturer).text = "Производитель ЦП: ${laptopDetails.cpuManufacturer}"
            findViewById<TextView>(R.id.laptopRam).text = "ОЗУ: ${laptopDetails.ram} ГБ"
        }
    }
}