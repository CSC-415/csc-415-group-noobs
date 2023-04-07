package com.example.bottomnav.data.di

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.entity.UserStat

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