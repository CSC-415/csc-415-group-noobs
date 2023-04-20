package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.entity.UserStat
import com.github.mikephil.charting.data.Entry
interface DatabaseRepositoryInterface {

    fun getAllTodoItems():LiveData<List<TodoItem>>

    suspend fun insert(toDosItem: TodoItem){

    }

    suspend fun update(toDosItem: TodoItem){

    }

    fun delete(toDosItem: TodoItem){

    }

//    DUMMY DATA FOR GRAPH
    fun generateRandomData(): List<Entry>

//    TOTAL TASKS / POMODORO FOR PROGRESS PAGE
    fun getTotalYAxisValues(): Int
    fun clearUsers()

    suspend fun addCompletedPomodoroBasedOnCategory(id: Int, category: String, duration: Long)

//    suspend fun AddPomodoroDurationBasedOnCategory(id: Int, category: String, duration: Long)
    suspend fun getAllUserStat(): List<UserStat>
    suspend fun insertUserStat(userStat: UserStat)
    suspend fun update(userStat: UserStat)
    fun delete(userStat: UserStat)
}