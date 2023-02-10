package com.kroger.classdemoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycleView = findViewById<RecyclerView>(R.id.laptop_recycle_view)

        recycleView.layoutManager = LinearLayoutManager(this)

        val laptops = createLaptops()

        val adapter = LaptopAdapter(laptops)

        recycleView.adapter = adapter
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