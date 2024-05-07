package com.example.mvvmlaptops.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlaptops.R
import com.example.mvvmlaptops.domain.LaptopModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: LaptopListAdapter
    private lateinit var viewModel: LaptopViewModel
    private var tempText = ""

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[LaptopViewModel::class.java]

        if (viewModel.newList.value == null)
            viewModel.getAllLaptops()

        adapter = LaptopListAdapter(mutableListOf())
        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.manufacturer_button).setOnClickListener {
            viewModel.filterManufacturer()
        }

        findViewById<Button>(R.id.cpu_button).setOnClickListener {
            viewModel.filterCpu()
        }

        findViewById<Button>(R.id.ram_button).setOnClickListener {
            viewModel.filterRam()
        }

        findViewById<Button>(R.id.sort_button).setOnClickListener {
            viewModel.sort(viewModel.filter)
        }

        findViewById<EditText>(R.id.search_field).addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString()
                if (text.isNotEmpty()) {
                    if (tempText.length > text.length) viewModel.getAllLaptops()
                    tempText = text
                    viewModel.search(text)
                } else viewModel.getAllLaptops()
            }
        })

        viewModel.manufacturerColor.observe(this) {
            findViewById<Button>(R.id.manufacturer_button).setBackgroundColor(Color.parseColor(it))
        }

        viewModel.cpuColor.observe(this) {
            findViewById<Button>(R.id.cpu_button).setBackgroundColor(Color.parseColor(it))
        }

        viewModel.ramColor.observe(this) {
            findViewById<Button>(R.id.ram_button).setBackgroundColor(Color.parseColor(it))
        }

        viewModel.newList.observe(this) {
            if (adapter != null) {
                adapter.list = it
            }
            adapter.notifyDataSetChanged()
        }

        adapter.setOnClickListener(object : LaptopListAdapter.OnClickListener {
            override fun onClick(position: Int, model: LaptopModel) {
                val intent = Intent(this@MainActivity, LaptopDetails::class.java)
                intent.putExtra(NEXT_SCREEN, model)
                startActivity(intent)
            }
        })
    }

    companion object Constants {
        val NEXT_SCREEN = "details"
    }
}