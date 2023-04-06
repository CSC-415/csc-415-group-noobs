package com.example.bottomnav.model.di

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.bottomnav.model.dao.TodoItemsDao
import com.example.bottomnav.model.dao.UserStatDao
import com.example.bottomnav.model.entity.TodoItem
import com.example.bottomnav.model.entity.UserStat

class DatabaseRepository(private val userStatDao: UserStatDao, private val todoItemsDao: TodoItemsDao) {

    ////////////////////////////////////////////////////////////
    //userStatDao functions
    suspend fun insertUserStat(userStat: UserStat) = userStatDao.insertUserStat(userStat)
    ///////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    //todoDao functions
    suspend fun insertTodoItem(todoItem: TodoItem) = todoItemsDao.insertTodoItem(todoItem)

    suspend fun deleteTodoItem(todoItem: TodoItem) = todoItemsDao.deleteTodoItem(todoItem)

    fun getAllTodoItems(): LiveData<List<TodoItem>> = todoItemsDao.getAllTodoItems()
    ////////////////////////////////////////////////////////////
}