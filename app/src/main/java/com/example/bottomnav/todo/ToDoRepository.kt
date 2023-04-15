package com.example.bottomnav.todo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnav.todo.db.ToDo
import com.example.bottomnav.todo.db.TodoDao

class ToDoRepository(private val todoDAO: TodoDao) {

    val allToDos: LiveData<List<ToDo>> = todoDAO.getAllToDo()

    suspend fun insert(toDos: ToDo){
        todoDAO.insert(toDos)
    }

    suspend fun update(toDos: ToDo){
        todoDAO.update(toDos)
    }

    fun delete(toDos: ToDo){
        todoDAO.delete(toDos)
    }

}