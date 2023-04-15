package com.example.bottomnav

import android.app.Application
import com.example.bottomnav.todo.ToDoRepository
import com.example.bottomnav.todo.db.AppDatabase

class ToDoApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ToDoRepository(database.getTodoDao()) }
}