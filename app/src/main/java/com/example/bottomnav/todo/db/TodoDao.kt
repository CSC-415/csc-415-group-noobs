package com.example.bottomnav.todo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM toDoTable order by priority DESC")
    fun getAllToDo(): LiveData<List<ToDo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDos: ToDo)

    @Update
    suspend fun update(toDos: ToDo)

    @Delete
    fun delete(toDo: ToDo)
}