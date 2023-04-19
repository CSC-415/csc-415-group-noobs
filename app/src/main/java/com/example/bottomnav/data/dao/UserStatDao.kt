package com.example.bottomnav.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnav.data.entity.UserStat

@Dao
interface UserStatDao {
    @Query("SELECT * FROM userTable")
    suspend fun getAllUserStat(): List<UserStat>

    @Insert
    suspend fun insert(userStat: UserStat)

    @Update
    suspend fun update(userStat: UserStat)

    @Delete
    fun delete(userStat: UserStat)

    @Query("DELETE FROM userTable")
    fun clear();
}