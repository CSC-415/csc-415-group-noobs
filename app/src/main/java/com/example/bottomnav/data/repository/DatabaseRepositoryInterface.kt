package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.github.mikephil.charting.data.Entry
import java.util.*

interface DatabaseRepositoryInterface {

    fun getAllTodoItems():LiveData<List<TodoItem>>

    suspend fun insert(toDosItem: TodoItem){

    }

    suspend fun update(toDosItem: TodoItem){

    }

    fun delete(toDosItem: TodoItem){

    }

//    DUMMY DATA FOR GRAPH
    fun generateRandomData(): List<Entry>

//    TOTAL TASKS / POMODORO FOR PROGRESS PAGE
    fun getTotalYAxisValues(): Int
}
