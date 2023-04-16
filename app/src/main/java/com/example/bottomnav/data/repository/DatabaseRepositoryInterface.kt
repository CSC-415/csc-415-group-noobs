package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.ToDoItem

interface DatabaseRepositoryInterface {

    fun getAllTodoItems():LiveData<List<ToDoItem>>

    suspend fun insert(toDosItem: ToDoItem){

    }

    suspend fun update(toDosItem: ToDoItem){

    }

    fun delete(toDosItem: ToDoItem){

    }

}