package com.example.myapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.databinding.FragmentProgressBinding
import com.example.myapp.viewmodel.ProgressViewModel
import com.github.mikephil.charting.data.Entry
import com.google.android.play.core.tasks.Task

class ProgressAdapter : RecyclerView.Adapter<ProgressAdapter.ViewHolder>() {

    private var tasksCompleted: Int = 0
    private var pomodorosCompleted: Int = 0

    fun setData(tasksCompleted: Int, pomodorosCompleted: Int) {
        this.tasksCompleted = tasksCompleted
        this.pomodorosCompleted = pomodorosCompleted
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.bind("Tasks Completed", tasksCompleted)
        } else {
            holder.bind("Pomodoros Completed", pomodorosCompleted)
        }
    }

    override fun getItemCount(): Int = 2

    inner class ViewHolder(private val binding: FragmentProgressBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String, number: Int) {
            binding.tasksButton.text = text
            binding.pomodoroButton.text = number.toString()
        }
    }
}
