package com.example.bottomnav.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnav.data.dao.UserStatDao
import com.example.bottomnav.data.entity.UserStat

@Database(entities = [UserStat::class], version = 5)
abstract class UserStatDatabase : RoomDatabase(){
    abstract fun getUserStatDao(): UserStatDao

}