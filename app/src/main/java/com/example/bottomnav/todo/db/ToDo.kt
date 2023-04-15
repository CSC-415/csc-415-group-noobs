package com.example.bottomnav.todo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDoTable")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "task")
    val task: String,
    @ColumnInfo(name = "priority")
    val priority: Int,
    @ColumnInfo(name = "status")
    val status: Boolean,
    @ColumnInfo(name = "dueDate")
    val dueDate: String
)