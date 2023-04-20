package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.entity.UserStat

interface DatabaseRepositoryInterface {
    //todoDAO
    fun getAllTodoItems():LiveData<List<TodoItem>>
    suspend fun insert(toDosItem: TodoItem)
    suspend fun update(toDosItem: TodoItem)
    fun delete(toDosItem: TodoItem)

    //userStatDAO
    suspend fun insertUserStat(userStat: UserStat)
    suspend fun addCompletedPomodoroBasedOnCategory(id: Int, category: String, duration: Long)
//    suspend fun AddPomodoroDurationBasedOnCategory(id: Int, category: String, duration: Long)
    fun isUsernameExists(username: String): Boolean
    fun login(username:String, password: String): Boolean
}