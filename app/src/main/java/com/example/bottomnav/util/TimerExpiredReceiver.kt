package com.example.bottomnav.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.bottomnav.home.ui.Home

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        PrefUtil.setTimerState(Home.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}