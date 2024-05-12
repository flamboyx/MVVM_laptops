package com.example.mvvmlaptops.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlaptops.R
import com.example.mvvmlaptops.domain.LaptopModel

class LaptopListAdapter(var list: MutableList<LaptopModel>) :
    RecyclerView.Adapter<LaptopListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val model: TextView = itemView.findViewById(R.id.laptopModel)
        val cpu: TextView = itemView.findViewById(R.id.laptopCpu)
        val ram: TextView = itemView.findViewById(R.id.laptopRam)
    }

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = R.layout.laptop_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val laptop = list[position]
        holder.model.text = laptop.model
        holder.cpu.text = "ЦП: ${laptop.cpu}"
        holder.ram.text = "ОЗУ: ${laptop.ram} ГБ"
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, laptop)
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: LaptopModel)
    }
}