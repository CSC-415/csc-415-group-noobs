package com.example.bottomnav.data.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.entity.UserStat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val todoItemsDAO: TodoItemsDao,
    private val userStatDAO: UserStatDao
): DatabaseRepositoryInterface {

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

    override suspend fun insertUserStat(userStat: UserStat){
        userStatDAO.insertUserStat(userStat)
    }

    override suspend fun addCompletedPomodoroBasedOnCategory(
        id: Int,
        category: String,
        duration: Long
    ) {
        if(category.equals("Study")){
            userStatDAO.addCompletedStudyPomodoroDuration(id, duration)
        }
        else if(category.equals("Work")){
            userStatDAO.addCompletedWorkPomodoroDuration(id, duration)
        }
        else if(category.equals("Exercise")){
            userStatDAO.addCompletedExercisePomodoroDuration(id, duration)
        }
        else if(category.equals("Relaxation")){
            userStatDAO.addCompletedRelaxPomodoroDuration(id, duration)
        }
        else{
            userStatDAO.addCompletedMiscellaneousPomodoroDuration(id, duration)
        }
    }

    override suspend fun isUsernameExists(username: String): Boolean = withContext(Dispatchers.IO){
        userStatDAO.isUsernameExists(username)
    }

    override suspend fun login(username: String, password: String): Boolean = withContext(Dispatchers.IO){
        userStatDAO.login(username, password)
    }
}