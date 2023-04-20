package com.example.bottomnav.login.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bottomnav.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel(){

    fun isLoginValid(username: String, password: String): Boolean = databaseRepository.login(username, password)

}