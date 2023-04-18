package com.example.bottomnav.util

import android.content.Context
import com.example.bottomnav.home.ui.Home
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PrefUtil @Inject constructor(
    @ApplicationContext appContext: Context
    ) : PrefUtilInterface {

    private val preferences = appContext.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)

    override fun getTimerLength(): Int{
        //placeholder
        return 5
    }

    override fun resetPrefUtil(){
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    override fun getPreviousTimerLengthSeconds(): Long{
        return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
    }

    override fun setPreviousTimerLengthSeconds(seconds: Long){
        val editor = preferences.edit()

        editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
        editor.apply()
    }

    override fun getTimerState(): Home.TimerState{
        val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
        return Home.TimerState.values()[ordinal]
    }

    override fun setTimerState(state: Home.TimerState){
        val editor = preferences.edit()

        editor.putInt(TIMER_STATE_ID, state.ordinal)
        editor.apply()
    }

    override fun getSecondsRemaining(): Long{
        return preferences.getLong(SECONDS_REMAINING_ID, 0)
    }

    override fun setSecondsRemaining(seconds: Long){
        val editor = preferences.edit()

        editor.putLong(SECONDS_REMAINING_ID, seconds)
        editor.apply()
    }

    override fun getAlarmSetTime(): Long{
        return preferences.getLong(ALARM_SET_TIME_ID, 0)
    }

    override fun setAlarmSetTime(timeInSeconds: Long){
        val editor = preferences.edit()

        editor.putLong(ALARM_SET_TIME_ID, timeInSeconds)
        editor.apply()
    }
    companion object {
        private const val SHARED_PREFERENCES = "timerPreferences"

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "previous_timer_length"
        private const val TIMER_STATE_ID = "timer_state"
        private const val SECONDS_REMAINING_ID = "seconds_remaining"
        private const val ALARM_SET_TIME_ID = "backgrounded_time"
    }

}