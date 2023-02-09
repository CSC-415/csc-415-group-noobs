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
            ramSizeGB = 1,
            image = R.drawable.baseline_10k_24,
            displayResolution = "Earth",
            storageSizeTB = 0,
            relation = listOf()
        ),

        Laptop(
            name = "Mac book Air M2",
            ramSizeGB = 1,
            image = R.drawable.baseline_10k_24,
            displayResolution = "Earth",
            storageSizeTB = 0,
            relation = listOf()
        ),

        Laptop(
            name = "HP Spectre x360",
            ramSizeGB = 1,
            image = R.drawable.baseline_10k_24,
            displayResolution = "Earth",
            storageSizeTB = 0,
            relation = listOf()
        ),

        Laptop(
            name = "ASUS ROG Zephyrus G15",
            ramSizeGB = 1,
            image = R.drawable.baseline_10k_24,
            displayResolution = "Earth",
            storageSizeTB = 0,
            relation = listOf()
        )
    )
}