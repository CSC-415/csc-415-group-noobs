package com.example.bottomnav.achievements.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.data.repository.DatabaseRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Math.floor
import javax.inject.Inject

@HiltViewModel
class AchievementsViewModel @Inject constructor(
    private val repository: DatabaseRepositoryInterface
) : ViewModel() {
    private lateinit var allUsers: List<UserStat>

    init {
        runBlocking {
            val job = viewModelScope.launch(Dispatchers.IO) {
                allUsers = repository.getAllUserStat()
            }
            job.join()
        }
    }

    fun getAllUsers() = allUsers

    fun addUser(userStat: UserStat) {
        runBlocking {
            val job = viewModelScope.launch(Dispatchers.IO) {
                repository.insert(userStat)
            }
            job.join()
        }
    }

    fun updateUser(userStat: UserStat) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(userStat)
    }

    fun deleteUser(userStat: UserStat) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(userStat)
    }

    fun clearUsers() {
        runBlocking {
            val job = viewModelScope.launch(Dispatchers.IO) {
                repository.clearUsers()
            }
            job.join()
        }
    }

    fun getLevels(userStat: UserStat): List<Int> {
        val consXP = userStat.consistencyExperiencePoints % 1000
        val todoXP = userStat.todoerExperiencePoints % 1000
        val studyXP = userStat.studierExperiencePoints % 1000
        val overallXP = (userStat.consistencyExperiencePoints +
                userStat.todoerExperiencePoints + userStat.studierExperiencePoints) % 3000
        val consLV = (userStat.consistencyExperiencePoints / 1000) + 1
        val todoLV = (userStat.todoerExperiencePoints / 1000) + 1
        val studyLV = (userStat.studierExperiencePoints / 1000) + 1
        val overallLV = ((userStat.consistencyExperiencePoints +
                userStat.todoerExperiencePoints + userStat.studierExperiencePoints) / 3000) + 1
        return listOf(overallLV, consLV, todoLV, studyLV, overallXP, consXP, todoXP, studyXP)
    }

}