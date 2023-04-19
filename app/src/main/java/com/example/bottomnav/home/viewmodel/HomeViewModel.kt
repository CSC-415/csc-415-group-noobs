package com.example.bottomnav.home.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.entity.UserStat
import com.example.bottomnav.data.repository.DatabaseRepository
import com.example.bottomnav.home.ui.Home
import com.example.bottomnav.util.PrefUtilInterface
import com.example.bottomnav.util.TimerExpiredReceiver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prefUtilInterface: PrefUtilInterface,
    private val databaseRepository: DatabaseRepository
): ViewModel() {
    private var selectedCategory: String = ""
    private var selectedCategoryTodoItem: String = ""
    private var userStat: UserStat = UserStat(0, "Biplov", "Ale", 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    init{
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.insertUserStat(userStat)
        }
    }

    companion object {
        val nowSeconds: Long get() = Calendar.getInstance().timeInMillis / 1000

        fun setAlarm(context: Context, prefUtilInterface: PrefUtilInterface, nowSeconds: Long, secondsRemaining: Long): Long {
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            prefUtilInterface.setAlarmSetTime(nowSeconds)
            return wakeUpTime
        }

        fun removeAlarm(context: Context, prefUtilInterface: PrefUtilInterface) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            prefUtilInterface.setAlarmSetTime(0)//0 -> alarm is not set
        }
    }

    fun setSelectedCategory(cat: String){
        selectedCategory = cat
    }

    fun getNowSeconds() = nowSeconds

    fun resetPrefUtil() = prefUtilInterface.resetPrefUtil()
    fun setBackgroundAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long):Long = setAlarm(context, prefUtilInterface, nowSeconds, secondsRemaining)
    fun removeBackgroundAlarm(context: Context) = removeAlarm(context, prefUtilInterface)

    fun getTimerLength(): Int = prefUtilInterface.getTimerLength()
    fun getPreviousTimerLengthSeconds(): Long = prefUtilInterface.getPreviousTimerLengthSeconds()
    fun getTimerState(): Home.TimerState = prefUtilInterface.getTimerState()
    fun getSecondsRemaining(): Long = prefUtilInterface.getSecondsRemaining()
    fun getAlarmSetTime(): Long = prefUtilInterface.getAlarmSetTime()

    fun setPrefUtilSelectedCategory(category: String) = prefUtilInterface.setSelectedCategory(category)

    fun setPreviousTimerLengthSeconds(seconds: Long) = prefUtilInterface.setPreviousTimerLengthSeconds(seconds)
    fun setTimerState(state: Home.TimerState) = prefUtilInterface.setTimerState(state)
    fun setSecondsRemaining(seconds: Long) = prefUtilInterface.setSecondsRemaining(seconds)

    fun recordCompletedPomodoroDuration() = viewModelScope.launch(Dispatchers.IO) {
        val pomodoroDurationMinutes = prefUtilInterface.getTimerLength()

        databaseRepository.addCompletedPomodoroBasedOnCategory(0, selectedCategory, getTimerLength() as Long)
    }

    fun recordPomodoroDuration(duration: Long) = viewModelScope.launch(Dispatchers.IO){
        databaseRepository.addCompletedPomodoroBasedOnCategory(0, selectedCategory, duration)
    }
}