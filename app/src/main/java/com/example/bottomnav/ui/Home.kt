package com.example.bottomnav.ui

import androidx.fragment.app.activityViewModels
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnav.databinding.FragmentHomeBinding
import com.example.bottomnav.util.PrefUtil
import com.example.bottomnav.util.PrefUtilInterface
import com.example.bottomnav.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    //binding data members
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel : HomeViewModel by activityViewModels()

    enum class TimerState{
        Stopped, Paused, Running
    }

    //timer data members
    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long= 0L
    private var timerState: TimerState = TimerState.Stopped
    private var secondsRemaining: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timerStart.setOnClickListener{view ->
            beginTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        binding.timerPause.setOnClickListener{ view ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        binding.timerStop.setOnClickListener{ view ->
            timer.cancel()
            endTimer()
        }
    }

    override fun onResume() {
        super.onResume()

        timerState = homeViewModel.getTimerState()

        initTimer()

        //backgrounded timer
        homeViewModel.removeBackgroundAlarm(requireContext())
    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running){
            //backgrounded timer
            val wakeUpTime = homeViewModel.setBackgroundAlarm(requireContext(), homeViewModel.getNowSeconds(), secondsRemaining)

        }
        else if (timerState == TimerState.Paused){

        }

        homeViewModel.setPreviousTimerLengthSeconds(timerLengthSeconds)
        homeViewModel.setSecondsRemaining(secondsRemaining)
        homeViewModel.setTimerState(timerState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    //other methods
    private fun initTimer(){
        timerState = homeViewModel.getTimerState()

        if(timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if(timerState == TimerState.Running || timerState == TimerState.Paused)
            homeViewModel.getSecondsRemaining()
        else
            timerLengthSeconds

        //change secondsRemaining according to where the background timer stopped
        val alarmSetTime = homeViewModel.getAlarmSetTime()
        if(alarmSetTime > 0)
            secondsRemaining -= homeViewModel.getNowSeconds() - alarmSetTime

        //resume timer where we left off
        if(secondsRemaining <= 0)
            endTimer()
        else if(timerState == TimerState.Running)
            beginTimer()

        updateButtons()
        updateTimerUI()
    }

    private fun endTimer(){
        timerState = TimerState.Stopped

        setNewTimerLength()

        homeViewModel.setSecondsRemaining(timerLengthSeconds)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateTimerUI()
    }

    private fun beginTimer(){
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining*1000, 1000){
            override fun onFinish() = endTimer()

            override fun onTick(milisUntilFinished: Long) {
                secondsRemaining = milisUntilFinished / 1000
                updateTimerUI()
            }
        }.start()
    }

    private fun setNewTimerLength(){
        val timerLengthInMinutes = homeViewModel.getTimerLength()
        timerLengthSeconds = timerLengthInMinutes * 60L
        binding.timer.timerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = homeViewModel.getPreviousTimerLengthSeconds()
        binding.timer.timerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun updateTimerUI(){
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinutesUntilFinished = secondsRemaining - (minutesUntilFinished * 60)
        val timerSecondsStr = secondsInMinutesUntilFinished.toString()

        binding.timer.timerNum.text = "$minutesUntilFinished:${
            if(timerSecondsStr.length == 2)
                timerSecondsStr
            else
                "0" + timerSecondsStr
        }"
        binding.timer.timerProgressBar.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons(){
        when (timerState){
            TimerState.Running ->{
                binding.timerStart.isEnabled = false
                binding.timerStop.isEnabled = true
                binding.timerPause.isEnabled = true
            }
            TimerState.Stopped ->{
                binding.timerStart.isEnabled = true
                binding.timerStop.isEnabled = false
                binding.timerPause.isEnabled = false
            }
            TimerState.Paused ->{
                binding.timerStart.isEnabled = true
                binding.timerStop.isEnabled = true
                binding.timerPause.isEnabled = false
            }
        }
    }
}