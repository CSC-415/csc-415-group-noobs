package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.entity.UserStat
import com.github.mikephil.charting.data.Entry

interface DatabaseRepositoryInterface {
    //todoDAO
    fun getAllTodoItems():LiveData<List<TodoItem>>
    suspend fun insert(toDosItem: TodoItem)
    suspend fun update(toDosItem: TodoItem)
    fun delete(toDosItem: TodoItem)

    //userStatDAO
    suspend fun insertUserStat(userStat: UserStat)
    suspend fun getAllUserStat(): List<UserStat>
    fun clearUsers()
    suspend fun addPomodoroDurationBasedOnCategory(id: Int, category: String, duration: Long)
    suspend fun isUsernameExists(username: String): Boolean
    suspend fun login(username:String, password: String): Boolean
    suspend fun update(userStat: UserStat)
    fun delete(userStat: UserStat)


//    DUMMY DATA FOR GRAPH
    fun generateRandomData(): List<Entry>

//    TOTAL TASKS / POMODORO FOR PROGRESS PAGE
    fun getTotalYAxisValues(): Int

}