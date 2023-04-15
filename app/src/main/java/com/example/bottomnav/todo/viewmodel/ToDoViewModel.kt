package com.example.bottomnav.todo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.bottomnav.todo.ToDoRepository
import com.example.bottomnav.todo.db.AppDatabase
import com.example.bottomnav.todo.db.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository : ToDoRepository): ViewModel() {
    val allToDos: LiveData<List<ToDo>>

    init {
        allToDos = repository.allToDos
    }

    fun deleteTodo(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(toDo)
    }

    fun updateTodo(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO){
        repository.update(toDo)
    }

    fun addTodo(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(toDo)
    }
}

class ToDoViewModelFactory(private val repository: ToDoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}