package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.entity.UserStat

interface DatabaseRepositoryInterface {

    fun getAllTodoItems():LiveData<List<TodoItem>>

    suspend fun insert(toDosItem: TodoItem)
    suspend fun update(toDosItem: TodoItem)
    fun delete(toDosItem: TodoItem)

    suspend fun insertUserStat(userStat: UserStat)

    suspend fun addCompletedPomodoroBasedOnCategory(id: Int, category: String, duration: Long)

//    suspend fun AddPomodoroDurationBasedOnCategory(id: Int, category: String, duration: Long)
}