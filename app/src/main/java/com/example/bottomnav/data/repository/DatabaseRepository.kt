package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.ToDoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val todoItemsDAO: TodoItemsDao): DatabaseRepositoryInterface {

    val allItemToDos: LiveData<List<ToDoItem>> = todoItemsDAO.getAllToDo()

    override fun getAllTodoItems() = allItemToDos

    override suspend fun insert(toDosItem: ToDoItem){
        todoItemsDAO.insert(toDosItem)
    }

    override suspend fun update(toDosItem: ToDoItem){
        todoItemsDAO.update(toDosItem)
    }

    override fun delete(toDosItem: ToDoItem){
        todoItemsDAO.delete(toDosItem)
    }

}