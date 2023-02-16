package com.kroger.classdemoapp

import android.content.DialogInterface.OnClickListener
import android.icu.text.Transliterator.Position
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kroger.classdemoapp.UI.LaptopDetailFragment

class LaptopAdapter(private val laptops: List<Laptop>) : RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaptopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.laptop_card_view, parent, false)

        return LaptopViewHolder(view) { position ->
            val laptop = laptops[position]

            val bundle = bundleOf(
                "name" to laptop.name,
                "ramSizeGB" to laptop.ramSizeGB,
                "displayResolution" to laptop.displayResolution,
                "storageSizeTB" to laptop.storageSizeTB,
                "image" to laptop.image
            )

            val detailFragment = LaptopDetailFragment()
            detailFragment.arguments = bundle

            val activity = view.context as AppCompatActivity

            activity.supportFragmentManager.commit{
                setReorderingAllowed(true)
                replace(R.id.fragment_container_view, detailFragment)
                addToBackStack(null)
            }

        }
    }

    override fun getItemCount() = laptops.size

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
        val laptop = laptops[position]

        Glide.with(holder.itemView.context).load(laptop.image).into(holder.laptopImage)

        holder.laptopName.text = laptop.name
        holder.laptopRamSize.text = buildString {
        append("Ram: ")
        append(laptop.ramSizeGB.toString())
        append(" GB")
    }
        holder.laptopDisplayResolution.text = buildString {
        append("Display Resolution: ")
        append(laptop.displayResolution)
    }
        holder.laptopStorageSize.text = buildString {
        append("SSD: ")
        append(laptop.storageSizeTB.toString())
        append(" TB")
    }
    }

    inner class LaptopViewHolder(
        itemView: View,
        private val onItemClick: (adapterPosition: Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        val laptopImage: ImageView = itemView.findViewById(R.id.laptop_image)
        val laptopName: TextView = itemView.findViewById(R.id.laptop_name)
        val laptopRamSize: TextView = itemView.findViewById(R.id.laptop_ram_size)
        val laptopDisplayResolution: TextView = itemView.findViewById(R.id.laptop_display_resolution)
        val laptopStorageSize: TextView = itemView.findViewById(R.id.laptop_storage_size)

        init{
            itemView.setOnClickListener{
                onItemClick(adapterPosition)
            }
        }
    }
}