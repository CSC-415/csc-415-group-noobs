package com.example.bottomnav.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoItemTable")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "todo_item_name")
    val task: String,
    @ColumnInfo(name = "todo_item_priority")
    val priority: Int,
    @ColumnInfo(name = "todo_item_is_completed")
    val status: Boolean,
    @ColumnInfo(name = "todo_item_date")
    val dueDate: String
)