package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.entity.UserStat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.github.mikephil.charting.data.Entry
import java.util.*
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val todoItemsDAO: TodoItemsDao,
    private val userStatDAO: UserStatDao
) : DatabaseRepositoryInterface {

    private var totalY = 0

    //todoDAO
    val allItemToDos: LiveData<List<TodoItem>> = todoItemsDAO.getAllToDo()
    override fun getAllTodoItems() = allItemToDos
    override suspend fun insert(toDosItem: TodoItem) = todoItemsDAO.insert(toDosItem)
    override suspend fun update(toDosItem: TodoItem) = todoItemsDAO.update(toDosItem)
    override fun delete(toDosItem: TodoItem) = todoItemsDAO.delete(toDosItem)

    //userStatDAO
    override suspend fun getAllUserStat(): List<UserStat> = userStatDAO.getAllUserStat()
    override suspend fun insertUserStat(userStat: UserStat) = userStatDAO.insertUserStat(userStat)
    override suspend fun update(userStat: UserStat) = userStatDAO.update(userStat)
    override fun delete(userStat: UserStat) = userStatDAO.delete(userStat)
    override fun clearUsers() = userStatDAO.clear()
    override suspend fun addPomodoroDurationBasedOnCategory(
        id: Int,
        category: String,
        duration: Long
    ) {
        if (category.equals("Study")) {
            userStatDAO.addStudyPomodoroDuration(id, duration)
        } else if (category.equals("Work")) {
            userStatDAO.addWorkPomodoroDuration(id, duration)
        } else if (category.equals("Exercise")) {
            userStatDAO.addExercisePomodoroDuration(id, duration)
        } else if (category.equals("Relaxation")) {
            userStatDAO.addRelaxPomodoroDuration(id, duration)
        } else {
            userStatDAO.addMiscellaneousPomodoroDuration(id, duration)
        }
    }
    override suspend fun isUsernameExists(username: String): Boolean = withContext(Dispatchers.IO){
        userStatDAO.isUsernameExists(username)
    }
    override suspend fun login(username: String, password: String): Boolean = withContext(Dispatchers.IO){
        userStatDAO.login(username, password)
    }

    //    DUMMY DATA FOR GRAPH
    override fun generateRandomData(): MutableList<Entry> {
        val data = mutableListOf<Entry>()
        val random = Random()

        for (i in 0..4) {
            val yVal = random.nextInt(10).toFloat()
            totalY += yVal.toInt()
            data.add(Entry(i.toFloat(), yVal))
        }
        return data
    }

    //    TOTAL TASKS / POMODORO FOR PROGRESS PAGE
    override fun getTotalYAxisValues(): Int {
        return totalY
    }
}