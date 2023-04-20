package com.example.bottomnav.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao


@Database(entities = [TodoItem::class], version = 5)
abstract class TodoItemDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoItemsDao

}