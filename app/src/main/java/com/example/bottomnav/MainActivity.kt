package com.example.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.bottomnav.achievements.ui.AchievementsFragment
import com.example.bottomnav.databinding.ActivityMainBinding
import com.example.bottomnav.home.ui.Home
import com.example.bottomnav.login.ui.Login
import com.example.bottomnav.todo.ui.ToDoFragment
import com.example.myapp.ui.progress.ProgressFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFrag(Login())
        binding.bottomNav.isVisible = false
    }

    private fun replaceFrag(fragment : Fragment) {
        val fragManager  = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frame_layout,fragment)
        fragTransaction.commit()
    }

    fun setItemSelectedOnBottomNav() {
        replaceFrag(Home())
        binding.bottomNav.isVisible = true

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFrag(Home())
                R.id.progress -> replaceFrag(ProgressFragment())
                R.id.achievements -> replaceFrag(AchievementsFragment())
                R.id.to_do -> replaceFrag(ToDoFragment())

                else -> {

                }
            }
            true
        }
    }
}