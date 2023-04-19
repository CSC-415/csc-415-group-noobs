package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.entity.UserStat
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val todoItemsDAO: TodoItemsDao,
    private val userStatDAO: UserStatDao
    ): DatabaseRepositoryInterface {

    private val allItemToDos: LiveData<List<TodoItem>> = todoItemsDAO.getAllToDo()
    //private val allUserStat: List<UserStat> = userStatDAO.getAllUserStat()

    override fun getAllTodoItems() = todoItemsDAO.getAllToDo()

    override suspend fun getAllUserStat() = userStatDAO.getAllUserStat()

    override suspend fun insert(toDosItem: TodoItem) = todoItemsDAO.insert(toDosItem)

    override suspend fun insert(userStat: UserStat) = userStatDAO.insert(userStat)

    override suspend fun update(toDosItem: TodoItem) = todoItemsDAO.update(toDosItem)

    override suspend fun update(userStat: UserStat) = userStatDAO.update(userStat)

    override fun delete(toDosItem: TodoItem) = todoItemsDAO.delete(toDosItem)

    override fun delete(userStat: UserStat) = userStatDAO.delete(userStat)

    override fun clearUsers() = userStatDAO.clear()

}