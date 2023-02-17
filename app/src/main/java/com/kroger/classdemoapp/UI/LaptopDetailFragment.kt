package com.kroger.classdemoapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kroger.classdemoapp.R
import java.util.jar.Attributes.Name


class LaptopDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_laptop_detail, container, false)

        if(arguments != null){
            val name = requireArguments().getString("name")
            val ramSizeGB = requireArguments().getInt("ramSizeGB")
            val displayResolution= requireArguments().getString("displayResolution")
            val storageSizeTB = requireArguments().getInt("storageSizeTB")
            val image = requireArguments().getString("image")
            val description = requireArguments().getInt("description")

            Glide.with(this).load(image).into(view.findViewById<ImageView>(R.id.laptop_detail_image))
            view.findViewById<TextView>(R.id.laptop_name).text = buildString {
                append("Laptop Model : ")
                append(name)}
            view.findViewById<TextView>(R.id.laptop_ram_size).text = buildString {
                append("Ram Size : ")
                append(ramSizeGB.toString())
                append(" GB") }
            view.findViewById<TextView>(R.id.laptop_display_resolution).text = buildString{
                append("Display Resolution : ")
                append(displayResolution) }
            view.findViewById<TextView>(R.id.laptop_storage_size).text = buildString {
                append("SSD Size : ")
                append(storageSizeTB.toString())
                append(" TB") }
            view.findViewById<TextView>(R.id.laptop_description).text = buildString{
                append(getString(description))}
        }


        return view

    }


}