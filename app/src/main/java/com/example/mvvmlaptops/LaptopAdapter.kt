package com.example.mvvmlaptops

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LaptopAdapter(private var laptops: List<Laptop>) :
    RecyclerView.Adapter<LaptopAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val model: TextView = itemView.findViewById(R.id.tv_model)
        val cpu: TextView = itemView.findViewById(R.id.tv_cpu)
        val ram: TextView = itemView.findViewById(R.id.tv_ram)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.laptop_item, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val laptop = laptops[position]
        holder.model.text = laptop.model
        holder.cpu.text = "CPU: ${laptop.cpu}"
        holder.ram.text = "RAM: ${laptop.ram}"
    }

    override fun getItemCount() = laptops.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newLaptops: List<Laptop>) {
        laptops = newLaptops
        notifyDataSetChanged()
    }
}