package com.example.bottomnav.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bottomnav.data.entity.UserStat

@Dao
interface UserStatDao {

    @Insert
    suspend fun insertUserStat(userStat: UserStat)

    @Query("SELECT EXISTS (SELECT * FROM userTable WHERE user_name = :username")
    fun isUsernameExists(username: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM userTable WHERE user_name = :username and password = :password")
    fun login(username: String, password: String): Boolean

    @Query("UPDATE userTable " +
            "SET total_study_duration = total_study_duration + :duration, " +
            "no_of_pomodoro_completed = no_of_pomodoro_completed + 1, " +
            "total_pomodoro_duration = total_pomodoro_duration + :duration " +
            "WHERE user_id = :id")
    suspend fun addCompletedStudyPomodoroDuration(id: Int, duration: Long)

    @Query("UPDATE userTable " +
            "SET total_work_duration = total_work_duration + :duration, " +
            "no_of_pomodoro_completed = no_of_pomodoro_completed + 1, " +
            "total_pomodoro_duration = total_pomodoro_duration + :duration " +
            "WHERE user_id = :id")
    suspend fun addCompletedWorkPomodoroDuration(id: Int, duration: Long)

    @Query("UPDATE userTable " +
            "SET total_exercise_duration = total_exercise_duration + :duration, " +
            "no_of_pomodoro_completed = no_of_pomodoro_completed + 1, " +
            "total_pomodoro_duration = total_pomodoro_duration + :duration " +
            "WHERE user_id = :id")
    suspend fun addCompletedExercisePomodoroDuration(id: Int, duration: Long)

    @Query("UPDATE userTable " +
            "SET total_relax_duration = total_relax_duration + :duration, " +
            "no_of_pomodoro_completed = no_of_pomodoro_completed + 1, " +
            "total_pomodoro_duration = total_pomodoro_duration + :duration " +
            "WHERE user_id = :id")
    suspend fun addCompletedRelaxPomodoroDuration(id: Int, duration: Long)

    @Query("UPDATE userTable " +
            "SET total_miscellaneous_duration = total_miscellaneous_duration + :duration, " +
            "no_of_pomodoro_completed = no_of_pomodoro_completed + 1, " +
            "total_pomodoro_duration = total_pomodoro_duration + :duration " +
            "WHERE user_id = :id")
    suspend fun addCompletedMiscellaneousPomodoroDuration(id: Int, duration: Long)

    //for half done pomodoros
//    @Query("UPDATE userTable " +
//            "SET total_:category_duration = :category, " +
//            "total_pomodoro_duration = total_pomodoro_duration + :duration" +
//            "WHERE userId = :id")
//    suspend fun AddPomodoroDurationBasedOnCategory(id: Int, category: String, duration: Long)
}