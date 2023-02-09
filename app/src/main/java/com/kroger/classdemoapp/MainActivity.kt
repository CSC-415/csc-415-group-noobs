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

        val laptops = mutableListOf<Laptop>()

        for(i in 0.. 30){
            laptops.add(createLaptops())
        }

        val adapter = LaptopAdapter(laptops)

        recycleView.adapter = adapter
    }

    private fun createLaptops() = Laptop(
        name = "",
        age = Random.nextInt(10, 99),
        image = R.drawable.baseline_10k_24,
        universe = "Earth",
        id = 0,
        relation = listOf()
    )
}