package com.example.bottomnav.util

import android.content.Context
import com.example.bottomnav.Home

class PrefUtil {
    companion object {

        fun getTimerLength(context: Context): Int{
            //placeholder
            return 5
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "com.example.timer.previous_timer_length"

        fun getPreviousTimerLengthSeconds(context: Context): Long{
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context){
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            val editor = preferences.edit()

            editor.putLong("PREVIOUS_TIMER_LENGTH_SECONDS_ID", seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.example.timer.timer_state"

        fun getTimerState(context: Context): Home.TimerState{
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return Home.TimerState.values()[ordinal]
        }

        fun setTimerState(state: Home.TimerState, context: Context){
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            val editor = preferences.edit()

            editor.putInt("TIMER_STATE_ID", state.ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "com.example.timer.seconds_remaining"

        fun getSecondsRemaining(context: Context): Long{
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds: Long, context: Context){
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            val editor = preferences.edit()

            editor.putLong("SECONDS_REMAINING_ID", seconds)
            editor.apply()
        }

        private const val ALARM_SET_TIME_ID = "com.example.timer.backgrounded_time"

        fun getAlarmSetTime(context: Context): Long{
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            return preferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(timeInSeconds: Long, context: Context){
            val preferences = context.getSharedPreferences("timerPreferences", Context.MODE_PRIVATE)
            val editor = preferences.edit()

            editor.putLong("ALARM_SET_TIME_ID", timeInSeconds)
            editor.apply()
        }
    }
}