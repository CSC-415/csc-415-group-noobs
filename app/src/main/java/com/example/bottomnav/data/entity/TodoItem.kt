package com.example.bottomnav.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoItemTable")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val itemId: Int,
    @ColumnInfo(name = "todo_item_name")
    val itemName: String,
    @ColumnInfo(name = "todo_item_date")
    val date: String,//format: MM/DD/YYYY
    @ColumnInfo(name = "todo_item_priority")
    val priority: Int,
    @ColumnInfo(name = "todo_item_is_completed")
    val isCompleted: Boolean
)
