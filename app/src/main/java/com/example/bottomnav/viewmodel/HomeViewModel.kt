package com.example.bottomnav.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.bottomnav.util.PrefUtil
import com.example.bottomnav.util.TimerExpiredReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val PrefUtil : PrefUtil
): ViewModel() {
    companion object{
        val nowSeconds: Long get() = Calendar.getInstance().timeInMillis / 1000
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long):Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context){
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)//0 -> alarm is not set
        }
    }

    fun setBackgroundAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long):Long = setAlarm(context, nowSeconds, secondsRemaining)

    fun removeBackgroundAlarm(context: Context) = removeAlarm(context)

    fun getNowSeconds() = nowSeconds
}