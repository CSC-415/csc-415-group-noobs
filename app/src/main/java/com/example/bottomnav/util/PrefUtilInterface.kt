package com.example.bottomnav.util

import com.example.bottomnav.home.ui.Home

interface PrefUtilInterface {
    fun getTimerLength(): Int

    fun resetPrefUtil()

    fun getPreviousTimerLengthSeconds(): Long

    fun setPreviousTimerLengthSeconds(seconds: Long)

    fun getTimerState(): Home.TimerState

    fun setTimerState(state: Home.TimerState)

    fun getSecondsRemaining(): Long

    fun setSecondsRemaining(seconds: Long)

    fun getAlarmSetTime(): Long

    fun setAlarmSetTime(timeInSeconds: Long)
}