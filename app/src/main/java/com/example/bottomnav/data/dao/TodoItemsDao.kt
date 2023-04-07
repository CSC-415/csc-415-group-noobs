package com.example.bottomnav.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bottomnav.data.entity.TodoItem

@Dao
interface TodoItemsDao {

    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

    @Query("SELECT * FROM todoItemTable")
    fun getAllTodoItems(): LiveData<List<TodoItem>>
}