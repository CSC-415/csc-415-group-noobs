package com.example.bottomnav.di

import android.content.Context
import androidx.room.Room
import com.example.bottomnav.data.dao.TodoItemsDao
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.database.TodoItemDatabase
import com.example.bottomnav.data.database.UserStatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UserStatModule {


    @Provides
    fun provideApUserStatDatabase(@ApplicationContext appContext: Context): UserStatDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            UserStatDatabase::class.java,
            "app_user_stat_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesUserStatDAO(userStatDatabase: UserStatDatabase): UserStatDao {
        return userStatDatabase.getUserStatDao()
    }
}