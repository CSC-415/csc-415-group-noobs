package com.example.bottomnav.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel() {
    private var isUsernameTaken = false

    fun isUsernameTaken(username: String): Boolean {
        runBlocking {
            val job = viewModelScope.launch(Dispatchers.IO) {
                isUsernameTaken = databaseRepository.isUsernameExists(username)
            }
            job.join()
        }

        return isUsernameTaken
    }

    fun insertUser(userStat: UserStat) = viewModelScope.launch(Dispatchers.IO) {
        databaseRepository.insertUserStat(userStat)
    }
}