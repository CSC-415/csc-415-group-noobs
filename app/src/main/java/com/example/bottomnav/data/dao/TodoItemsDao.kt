package com.example.bottomnav.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnav.data.entity.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemsDao {
    @Query("SELECT * FROM todoItemTable WHERE todo_item_is_completed = 0 order by todo_item_priority DESC")
    fun getAllToDo(): LiveData<List<TodoItem>>

    @Query("SELECT * FROM todoItemTable WHERE todo_item_is_completed = 0 order by todo_item_priority Asc")
    fun getAllToDoAsc(): LiveData<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDosItem: TodoItem)

    @Update
    suspend fun update(toDosItem: TodoItem)

    @Delete
    fun delete(toDoItem: TodoItem)

}