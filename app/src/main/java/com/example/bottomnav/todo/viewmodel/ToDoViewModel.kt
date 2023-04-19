package com.example.bottomnav.todo.viewmodel

import androidx.lifecycle.*
import com.example.bottomnav.data.entity.TodoItem
import com.example.bottomnav.data.repository.DatabaseRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val repository : DatabaseRepositoryInterface
    ): ViewModel() {

    val allItemToDos: LiveData<List<TodoItem>>

    init {
        allItemToDos = repository.getAllTodoItems()
    }

    fun deleteTodo(toDoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(toDoItem)
    }

    fun updateTodo(toDoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.update(toDoItem)
    }

    fun addTodo(toDoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(toDoItem)
    }
}
