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

        val recyclerView = view.findViewById<RecyclerView>(R.id.laptop_recycler_view)

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
            image = "https://i0.wp.com/liliputing.com/wp-content/uploads/2019/12/xps13_02.jpg?fit=1200%2C950&ssl=1",
            displayResolution = "1920*1080",
            storageSizeTB = 2
        ),

        Laptop(
            name = "Mac book Air M2",
            ramSizeGB = 8,
            image = "https://techcrunch.com/wp-content/uploads/2022/07/CMC_1580.jpg",
            displayResolution = "2560*1600",
            storageSizeTB = 1
        ),

        Laptop(
            name = "HP Spectre x360",
            ramSizeGB = 16,
            image = "https://i.pcmag.com/imagery/reviews/00uwiwERhM15aoADGVjjUUy-1.jpg",
            displayResolution = "3840*2160",
            storageSizeTB = 1
        ),

        Laptop(
            name = "ASUS ROG Zephyrus G15",
            ramSizeGB = 32,
            image = "https://cdn.shopify.com/s/files/1/0228/7629/1136/products/10_real_scar_15_l_9ba26284-960d-40fe-9e8c-3f881b2ba086_2000x.png?v=1657751794",
            displayResolution = "2560*1440",
            storageSizeTB = 8
        )
    )


}