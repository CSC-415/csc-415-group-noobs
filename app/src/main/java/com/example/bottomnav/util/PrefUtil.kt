package com.example.bottomnav.util

import android.content.Context
import com.example.bottomnav.ui.Home

class PrefUtil {
    companion object {
    fun getTimerLength(context: Context): Int{
        //placeholder
        return 5
    }

    fun getPreviousTimerLengthSeconds(context: Context): Long{
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
    }

    fun setPreviousTimerLengthSeconds(seconds: Long, context: Context){
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
        editor.apply()
    }

    fun getTimerState(context: Context): Home.TimerState{
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
        return Home.TimerState.values()[ordinal]
    }

    fun setTimerState(state: Home.TimerState, context: Context){
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putInt(TIMER_STATE_ID, state.ordinal)
        editor.apply()
    }

    fun getSecondsRemaining(context: Context): Long{
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getLong(SECONDS_REMAINING_ID, 0)
    }

    fun setSecondsRemaining(seconds: Long, context: Context){
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putLong(SECONDS_REMAINING_ID, seconds)
        editor.apply()
    }

    fun getAlarmSetTime(context: Context): Long{
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        return preferences.getLong(ALARM_SET_TIME_ID, 0)
    }

    fun setAlarmSetTime(timeInSeconds: Long, context: Context){
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        editor.putLong(ALARM_SET_TIME_ID, timeInSeconds)
        editor.apply()
    }

        private const val SHARED_PREFERENCES = "timerPreferences"
        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "previous_timer_length"
        private const val TIMER_STATE_ID = "timer_state"
        private const val SECONDS_REMAINING_ID = "seconds_remaining"
        private const val ALARM_SET_TIME_ID = "backgrounded_time"
    }
}