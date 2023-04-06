package com.example.bottomnav.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnav.model.dao.UserStatDao
import com.example.bottomnav.model.entity.UserStat

@Database(entities = [UserStat::class], version = 1)
abstract class UserStatDatabase : RoomDatabase(){

    abstract fun userStatDao(): UserStatDao

    companion object{
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getUserStatDatabase(context: Context): RoomDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserStatDatabase::class.java,
                    "user_stat_database"
                ).build()
                INSTANCE= instance
                return instance
            }
        }
    }

}