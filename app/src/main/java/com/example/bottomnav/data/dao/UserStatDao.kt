package com.example.bottomnav.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.bottomnav.data.entity.UserStat

@Dao
interface UserStatDao {

    @Insert
    suspend fun insertUserStat(userStat: UserStat)
}