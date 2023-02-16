package com.kroger.classdemoapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kroger.classdemoapp.Laptop
import com.kroger.classdemoapp.LaptopAdapter
import com.kroger.classdemoapp.R


class LaptopListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_laptop_list, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.laptop_recycle_view)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val laptops = createLaptops()

        val adapter = LaptopAdapter(laptops)
        recyclerView.adapter = adapter

        return view

    }

    private fun createLaptops() : List<Laptop> = listOf(
        Laptop(
            name = "Dell XPS 13",
            ramSizeGB = 16,
            image = R.drawable.dell_xps_13,
            displayResolution = "1920*1080",
            storageSizeTB = 2
        ),

        Laptop(
            name = "Mac book Air M2",
            ramSizeGB = 8,
            image = R.drawable.macbook_air,
            displayResolution = "2560*1600",
            storageSizeTB = 1
        ),

        Laptop(
            name = "HP Spectre x360",
            ramSizeGB = 16,
            image = R.drawable.hp_spectre_x360,
            displayResolution = "3840*2160",
            storageSizeTB = 1
        ),

        Laptop(
            name = "ASUS ROG Zephyrus G15",
            ramSizeGB = 32,
            image = R.drawable.asus_g15,
            displayResolution = "2560*1440",
            storageSizeTB = 8
        )
    )


}