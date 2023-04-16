package com.example.bottomnav.todo.viewmodel

import androidx.lifecycle.*
import com.example.bottomnav.data.repository.DatabaseRepository
import com.example.bottomnav.data.entity.ToDoItem
import com.example.bottomnav.data.repository.DatabaseRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository : DatabaseRepositoryInterface
    ): ViewModel() {

    val allItemToDos: LiveData<List<ToDoItem>>

    init {
        allItemToDos = repository.getAllTodoItems()
    }

    fun deleteTodo(toDoItem: ToDoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(toDoItem)
    }

    fun updateTodo(toDoItem: ToDoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.update(toDoItem)
    }

    fun addTodo(toDoItem: ToDoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(toDoItem)
    }
}
