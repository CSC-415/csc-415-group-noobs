package com.example.bottomnav.di

import android.content.Context
import androidx.room.Room
import com.example.bottomnav.data.database.AppDatabase
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideAppTodoDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            AppDatabase::class.java,
            "app_todo_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesTodoDAO(appDatabase: AppDatabase): TodoItemsDao {
        return appDatabase.getTodoDao()
    }

    @Provides
    fun providesUserStatDAO(AppDatabase: AppDatabase): UserStatDao {
        return AppDatabase.getUserStatDao()
    }

}