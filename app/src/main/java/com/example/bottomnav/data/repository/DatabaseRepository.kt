package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import com.github.mikephil.charting.data.Entry
import java.util.*
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val todoItemsDAO: TodoItemsDao): DatabaseRepositoryInterface {

    private var totalY = 0

    val allItemToDos: LiveData<List<TodoItem>> = todoItemsDAO.getAllToDo()

    override fun getAllTodoItems() = allItemToDos

    override suspend fun insert(toDosItem: TodoItem){
        todoItemsDAO.insert(toDosItem)
    }

    override suspend fun update(toDosItem: TodoItem){
        todoItemsDAO.update(toDosItem)
    }

    override fun delete(toDosItem: TodoItem){
        todoItemsDAO.delete(toDosItem)
    }


    //    DUMMY DATA FOR GRAPH
    override fun generateRandomData(): MutableList<Entry> {
        val data = mutableListOf<Entry>()
        val random = Random()

        for (i in 0..4) {
            val yVal = random.nextInt(10).toFloat()
            totalY += yVal.toInt()
            data.add(Entry(i.toFloat(), yVal))
        }
        return data
    }
    //    TOTAL TASKS / POMODORO FOR PROGRESS PAGE
    override fun getTotalYAxisValues(): Int {
        return totalY
    }

}