package com.example.bottomnav.util

import android.content.Context
import com.example.bottomnav.ui.Home
import javax.inject.Inject

interface PrefUtilInterface {
    fun getTimerLength(): Int

    fun getPreviousTimerLengthSeconds(): Long

    fun setPreviousTimerLengthSeconds(seconds: Long)

    fun getTimerState(): Home.TimerState

    fun setTimerState(state: Home.TimerState)

    fun getSecondsRemaining(): Long

    fun setSecondsRemaining(seconds: Long)

    fun getAlarmSetTime(): Long

    fun setAlarmSetTime(timeInSeconds: Long)
}