package com.example.bottomnav.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bottomnav.data.entity.ToDoItem
import com.example.bottomnav.data.dao.TodoItemsDao


@Database(entities = [ToDoItem::class], version = 2)
abstract class TodoItemDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoItemsDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//
//                return tempInstance
//            }
//            synchronized(this) {
//
//
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}