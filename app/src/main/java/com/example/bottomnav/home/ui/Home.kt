package com.example.bottomnav.home.ui

import androidx.fragment.app.activityViewModels
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.bottomnav.databinding.FragmentHomeBinding
import com.example.bottomnav.home.viewmodel.HomeViewModel
import com.example.bottomnav.util.PrefUtilInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    //binding data members
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    enum class TimerState {
        Stopped, Paused, Running
    }

    //timer data members
    private var timer: CountDownTimer = object : CountDownTimer(0, 0) {
        override fun onFinish() {}
        override fun onTick(milisUntilFinished: Long) {}
    }
    private var timerLengthSeconds: Long = 0L
    private var timerState: TimerState = TimerState.Stopped
    private var secondsRemaining: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.resetPrefUtil()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesDropDown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.categoriesDropDown.setSelection(position)
                    homeViewModel.setSelectedCategory(
                        parent?.getItemAtPosition(position).toString()
                    )
                    homeViewModel.setPrefUtilSelectedCategory(
                        parent?.getItemAtPosition(position).toString()
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    binding.categoriesDropDown.setSelection(0)
                    homeViewModel.setSelectedCategory(parent?.getItemAtPosition(0).toString())
                    homeViewModel.setPrefUtilSelectedCategory(
                        parent?.getItemAtPosition(0).toString()
                    )
                }
            }

        binding.timerStart.setOnClickListener { view ->
            beginTimer()
            timerState = TimerState.Running
            updateButtons()
        }

        binding.timerPause.setOnClickListener { view ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        binding.timerStop.setOnClickListener { view ->
            timer.cancel()
            homeViewModel.recordPomodoroDuration(timerLengthSeconds - secondsRemaining)
            endTimer()
        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

        //backgrounded timer
        homeViewModel.removeBackgroundAlarm(requireContext())
    }

    override fun onPause() {
        super.onPause()

        if (timerState == TimerState.Running) {
            //backgrounded timer
            val wakeUpTime = homeViewModel.setBackgroundAlarm(
                requireContext(),
                homeViewModel.getNowSeconds(),
                secondsRemaining
            )

        } else if (timerState == TimerState.Paused) {

        }

        homeViewModel.setPreviousTimerLengthSeconds(timerLengthSeconds)
        homeViewModel.setSecondsRemaining(secondsRemaining)
        homeViewModel.setTimerState(timerState)
        timer.cancel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    //other methods
    private fun initTimer() {
        timerState = homeViewModel.getTimerState()

        if (timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused)
            homeViewModel.getSecondsRemaining()
        else
            timerLengthSeconds

        val alarmSetTime = homeViewModel.getAlarmSetTime()
        //change secondsRemaining according to where the background timer stopped
        if (alarmSetTime > 0)
            secondsRemaining -= homeViewModel.getNowSeconds() - alarmSetTime

        //resume timer where we left off
        if (secondsRemaining <= 0)
            endTimer()
        else if (timerState == TimerState.Running)
            beginTimer()

        updateButtons()
        updateTimerUI()
    }

    private fun endTimer() {
        timerState = TimerState.Stopped

        setNewTimerLength()

        binding.timer.timerProgressBar.progress = 0

        homeViewModel.setSecondsRemaining(timerLengthSeconds)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateTimerUI()
    }

    private fun beginTimer() {
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() {
                endTimer()
                homeViewModel.recordCompletedPomodoroDuration()
            }

            override fun onTick(milisUntilFinished: Long) {
                secondsRemaining = milisUntilFinished / 1000
                updateTimerUI()
            }
        }.start()
    }

    private fun setNewTimerLength() {
        val timerLengthInMinutes = homeViewModel.getTimerLength()
        timerLengthSeconds = timerLengthInMinutes * 60L
        binding.timer.timerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength() {
        timerLengthSeconds = homeViewModel.getPreviousTimerLengthSeconds()
        binding.timer.timerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun updateTimerUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinutesUntilFinished = secondsRemaining - (minutesUntilFinished * 60)
        val timerSecondsStr = secondsInMinutesUntilFinished.toString()

        binding.timer.timerNum.text = "$minutesUntilFinished:${
            if (timerSecondsStr.length == 2)
                timerSecondsStr
            else
                "0" + timerSecondsStr
        }"
        binding.timer.timerProgressBar.progress =
            (timerLengthSeconds - (timerLengthSeconds - secondsRemaining)).toInt()
    }

    private fun updateButtons() {
        when (timerState) {
            TimerState.Running -> {
                binding.timerStart.isEnabled = false
                binding.timerStop.isEnabled = true
                binding.timerPause.isEnabled = true
            }
            TimerState.Stopped -> {
                binding.timerStart.isEnabled = true
                binding.timerStop.isEnabled = false
                binding.timerPause.isEnabled = false
            }
            TimerState.Paused -> {
                binding.timerStart.isEnabled = true
                binding.timerStop.isEnabled = true
                binding.timerPause.isEnabled = false
            }
        }
    }
}