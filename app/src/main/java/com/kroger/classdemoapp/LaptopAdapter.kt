package com.kroger.classdemoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LaptopAdapter(private val laptops: List<Laptop>) : RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {


    class LaptopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val laptopImage: ImageView = itemView.findViewById(R.id.laptop_image)
        val laptopName: TextView = itemView.findViewById(R.id.laptop_name)
        val laptopRamSize: TextView = itemView.findViewById(R.id.laptop_ram_size)
        val laptopDisplayResolution: TextView = itemView.findViewById(R.id.laptop_display_resolution)
        val laptopStorageSize: TextView = itemView.findViewById(R.id.laptop_storage_size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.laptop_card_view, parent, false)
        return LaptopViewHolder(view)
    }

    override fun getItemCount() = laptops.size

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        val laptop = laptops[position]
        holder.laptopImage.setImageResource(laptop.image)
        holder.laptopName.text = laptop.name
        holder.laptopRamSize.text = laptop.ramSizeGB.toString()
        holder.laptopDisplayResolution.text = laptop.displayResolution
        holder.laptopStorageSize.text = laptop.storageSizeTB.toString()
    }
}