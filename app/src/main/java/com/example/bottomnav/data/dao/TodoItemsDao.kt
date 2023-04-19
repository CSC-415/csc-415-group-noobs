package com.example.bottomnav.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnav.data.entity.TodoItem

@Dao
interface TodoItemsDao {
    @Query("SELECT * FROM todoItemTable order by todo_item_priority DESC")
    fun getAllToDo(): LiveData<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDosItem: TodoItem)

    @Update
    suspend fun update(toDosItem: TodoItem)

    @Delete
    fun delete(toDoItem: TodoItem)
}