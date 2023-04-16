package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem

interface DatabaseRepositoryInterface {

    fun getAllTodoItems():LiveData<List<TodoItem>>

    suspend fun insert(toDosItem: TodoItem){

    }

    suspend fun update(toDosItem: TodoItem){

    }

    fun delete(toDosItem: TodoItem){

    }

}