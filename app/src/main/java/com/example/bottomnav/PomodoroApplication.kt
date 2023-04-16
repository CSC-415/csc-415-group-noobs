package com.example.bottomnav

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PomodoroApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val database by lazy { AppDatabase.getDatabase(this) }
//    val repository by lazy { ToDoRepository(database.getTodoDao()) }
}