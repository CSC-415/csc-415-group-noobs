package com.example.bottomnav.achievements.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.bottomnav.achievements.viewmodel.AchievementsViewModel
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.databinding.FragmentAchievementsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AchievementsFragment : Fragment() {
    private val viewModel: AchievementsViewModel by viewModels()
    private var _binding: FragmentAchievementsBinding? = null
    private val binding get() = _binding!!
    private var allUserStat: List<UserStat> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAchievementsBinding.inflate(inflater, container, false)

        bind()

        return binding.root
    }


    @SuppressLint("SetTextI18n")
    fun bind() {
        allUserStat = viewModel.getAllUsers()

        if (!allUserStat.isNullOrEmpty()) {
            val user = allUserStat[0]
            val levels = viewModel.getLevels(user)
            binding.achievementsPoints.text = user.currencyPoints.toString() + " Points"
            binding.achievementsStreak.text = user.noOfPomodoroStreaks.toString() + " Day Streak"
            binding.achievementsOverallLevel.text = "LV " + levels[0].toString()
            binding.achievementsOverallXp.text = levels[4].toString() + " / 3000"
            binding.achievementsOverallXpbar.progress = levels[4]
            binding.achievementsConsLevel.text = "LV " + levels[1].toString()
            binding.achievementsConsXp.text = levels[5].toString() + " / 1000"
            binding.achievementsConsXpbar.progress = levels[5]
            binding.achievementsTodoLevel.text = "LV " + levels[2].toString()
            binding.achievementsTodoXp.text = levels[6].toString() + " / 1000"
            binding.achievementsTodoXpbar.progress = levels[6]
            binding.achievementsStudierLevel.text = "LV " + levels[3].toString()
            binding.achievementsStudierXp.text = levels[7].toString() + " / 1000"
            binding.achievementsStudierXpbar.progress = levels[7]
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}