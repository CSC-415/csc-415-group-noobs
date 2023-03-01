package com.example.bottomnav

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.health.TimerStat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.RequestPermissionsRequestCodeValidator
import com.example.bottomnav.databinding.FragmentHomeBinding
import com.example.bottomnav.util.PrefUtil
import com.example.bottomnav.util.TimerExpiredReceiver
import java.util.*
import kotlin.concurrent.timer

class Home : Fragment() {
    companion object{
        val nowSeconds: Long get() = Calendar.getInstance().timeInMillis / 1000
        fun setAlarm(activity: Activity, nowSeconds: Long, secondsRemaining: Long):Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(activity, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, activity)
            return wakeUpTime
        }

        fun removeAlarm(activity: Activity){
            val intent = Intent(activity, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, 0)
            val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, activity)//0 -> alarm is not set
        }
    }

    //binding data members
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
            TimerState.Paused
            updateButtons()
        }

        binding.timerStop.setOnClickListener{ view ->
            timer.cancel()
            endTimer()
        }
    }

    override fun onResume() {
        super.onResume()

        initTimer()

        //backgrounded timer
        removeAlarm(requireActivity())
    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running){
            //backgrounded timer
            val wakeUpTime = setAlarm(requireActivity(), nowSeconds, secondsRemaining)
        }
        else if (timerState == TimerState.Paused){

        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, requireContext())
        PrefUtil.setSecondsRemaining(secondsRemaining, requireContext())
        PrefUtil.setTimerState(timerState, requireContext())
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
        timerState = PrefUtil.getTimerState(requireContext())

        if(timerState == TimerState.Stopped)
            setNewTimerLength()
        else
            setPreviousTimerLength()

        secondsRemaining = if(timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(requireContext())
        else
            timerLengthSeconds

        //change secondsRemaining according to where the background timer stopped
        val alarmSetTime = PrefUtil.getAlarmSetTime(requireContext())
        if(alarmSetTime > 0)
            secondsRemaining -= nowSeconds - alarmSetTime

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

        PrefUtil.setSecondsRemaining(timerLengthSeconds, requireContext())
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
        val timerLengthInMinutes = PrefUtil.getTimerLength(requireContext())
        timerLengthSeconds = timerLengthInMinutes * 60L
        binding.timer.timerProgressBar.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(requireContext())
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