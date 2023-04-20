package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val todoItemsDAO: TodoItemsDao): DatabaseRepositoryInterface {

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

}