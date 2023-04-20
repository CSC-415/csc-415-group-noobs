package com.example.bottomnav.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.entity.UserStat


@Database(entities = [TodoItem::class, UserStat::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoItemsDao

    abstract fun getUserStatDao(): UserStatDao

}