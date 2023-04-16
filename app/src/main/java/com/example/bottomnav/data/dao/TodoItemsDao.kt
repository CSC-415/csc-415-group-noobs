package com.example.bottomnav.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnav.data.entity.ToDoItem

@Dao
interface TodoItemsDao {
    @Query("SELECT * FROM todoItemTable order by todo_item_priority DESC")
    fun getAllToDo(): LiveData<List<ToDoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDosItem: ToDoItem)

    @Update
    suspend fun update(toDosItem: ToDoItem)

    @Delete
    fun delete(toDoItem: ToDoItem)
}