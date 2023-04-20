package com.example.bottomnav.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : ViewModel(){
    private var isLoginValid = false

    fun isLoginValid(username: String, password: String):Boolean {
        runBlocking{
            val job = viewModelScope.launch(Dispatchers.IO) {
                isLoginValid = databaseRepository.login(username, password)
            }
            job.join()
        }

        return isLoginValid
    }

}