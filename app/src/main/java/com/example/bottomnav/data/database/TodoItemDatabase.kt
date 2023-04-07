package com.example.bottomnav.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.entity.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoItemDatabase: RoomDatabase(){

    abstract fun todoItemDao(): TodoItemsDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getTodoItemDatabase(context: Context):RoomDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoItemDatabase::class.java,
                    "todo_item_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}