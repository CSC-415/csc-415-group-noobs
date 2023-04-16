package com.example.bottomnav.home.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.bottomnav.home.ui.Home
import com.example.bottomnav.util.PrefUtilInterface
import com.example.bottomnav.util.TimerExpiredReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prefUtilInterface: PrefUtilInterface
): ViewModel() {
    companion object {
        val nowSeconds: Long get() = Calendar.getInstance().timeInMillis / 1000
    }
    fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long):Long{
        val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, TimerExpiredReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
        prefUtilInterface.setAlarmSetTime(nowSeconds)
        return wakeUpTime
    }

    fun removeAlarm(context: Context){
        val intent = Intent(context, TimerExpiredReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        prefUtilInterface.setAlarmSetTime(0)//0 -> alarm is not set
    }

    fun getNowSeconds() = nowSeconds
    fun setBackgroundAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long):Long = setAlarm(context, nowSeconds, secondsRemaining)
    fun removeBackgroundAlarm(context: Context) = removeAlarm(context)

    fun getTimerLength(): Int = prefUtilInterface.getTimerLength()
    fun getPreviousTimerLengthSeconds(): Long = prefUtilInterface.getPreviousTimerLengthSeconds()
    fun getTimerState(): Home.TimerState = prefUtilInterface.getTimerState()
    fun getSecondsRemaining(): Long = prefUtilInterface.getSecondsRemaining()
    fun getAlarmSetTime(): Long = prefUtilInterface.getAlarmSetTime()

    fun setPreviousTimerLengthSeconds(seconds: Long) = prefUtilInterface.setPreviousTimerLengthSeconds(seconds)
    fun setTimerState(state: Home.TimerState) = prefUtilInterface.setTimerState(state)
    fun setSecondsRemaining(seconds: Long) = prefUtilInterface.setSecondsRemaining(seconds)
    fun setAlarmSetTime(timeInSeconds: Long) = prefUtilInterface.setAlarmSetTime(timeInSeconds)
}