package com.example.bottomnav.di

import android.content.Context
import androidx.room.Room
import com.example.bottomnav.data.database.TodoItemDatabase
import com.example.bottomnav.data.dao.TodoItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ToDoModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): TodoItemDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            TodoItemDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesTodoDAO(todoItemDatabase: TodoItemDatabase): TodoItemsDao {
        return todoItemDatabase.getTodoDao()
    }
}