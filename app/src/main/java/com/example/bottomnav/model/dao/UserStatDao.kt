package com.example.bottomnav.model.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.bottomnav.model.entity.UserStat

@Dao
interface UserStatDao {

    @Insert
    suspend fun insertUserStat(userStat: UserStat)
}