package com.example.bottomnav.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bottomnav.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel() {

    fun isUsernameTaken(username: String): Boolean = databaseRepository.isUsernameExists(username)
}