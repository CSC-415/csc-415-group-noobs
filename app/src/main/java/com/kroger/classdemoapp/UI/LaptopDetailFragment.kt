package com.kroger.classdemoapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kroger.classdemoapp.R


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
            val image = requireArguments().getInt("image")
        }

        return view

    }


}