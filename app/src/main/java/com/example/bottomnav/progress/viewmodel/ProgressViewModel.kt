package com.example.myapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bottomnav.data.repository.DatabaseRepositoryInterface
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val repository : DatabaseRepositoryInterface
    ): ViewModel() {

    private val _tasksCompletedData = MutableLiveData<List<Entry>>()
    val tasksCompletedData: LiveData<List<Entry>>
        get() = _tasksCompletedData

    private val _pomodorosCompletedData = MutableLiveData<List<Entry>>()
    val pomodorosCompletedData: LiveData<List<Entry>>
        get() = _pomodorosCompletedData

    init {
        repository.generateRandomData()
    }

    fun setTasksCompletedData() {
        _tasksCompletedData.value = repository.generateRandomData()
    }

    fun setPomodorosCompletedData() {
        _pomodorosCompletedData.value = repository.generateRandomData()
    }

    fun getDefaultTasksCompletedData(): List<Entry> {
        return repository.generateRandomData()
    }

    fun getTotalTasks() : Int {
        repository.generateRandomData()
        return repository.getTotalYAxisValues()
    }
    fun getTotalPomodoros() : Int {
        repository.generateRandomData()
        return repository.getTotalYAxisValues()
    }

}

