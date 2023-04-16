package com.example.bottomnav.data.entity

import androidx.room.*

@Entity(tableName = "userTable")
data class UserStat (
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    @ColumnInfo(name = "user_first_name")
    val firstName: String,
    @ColumnInfo(name = "user_last_name")
    val lastName: String,
    @ColumnInfo(name = "user_age")
    val age: Int,
    @ColumnInfo(name = "consistency_experience_points")
    val consistencyExperiencePoints: Int,
    @ColumnInfo(name = "todoer_experience_points")
    val todoerExperiencePoints: Int,
    @ColumnInfo(name = "studier_experience_points")
    val studierExperiencePoints: Int,
    @ColumnInfo(name = "user_currency_points")
    val currencyPoints: Int,
    @ColumnInfo(name = "no_of_todo_items_completed")
    val todoItemsCompleted: Int,
    @ColumnInfo(name = "total_pomodoro_duration")
    val totalPomodoroDuration: String,//home everytime the pomodoro ends add here
    @ColumnInfo(name = "total_study_duration")
    val totalStudyDuration: String,
    @ColumnInfo(name = "total_work_duration")
    val totalWorkDuration: String,
    @ColumnInfo(name = "total_exercise_duration")
    val totalExerciseDuration: String,
    @ColumnInfo(name = "total_relax_duration")
    val totalRelaxDuration: String,
    @ColumnInfo(name = "total_misc_duration")
    val totalMiscDuration: String,
    @ColumnInfo(name = "no_of_pomodoro_streaks")
    val noOfPomodoroStreaks: Int,
    @ColumnInfo(name = "no_of_pomodoro_completed")
    val noOfPomodoroCompleted: Int
)

