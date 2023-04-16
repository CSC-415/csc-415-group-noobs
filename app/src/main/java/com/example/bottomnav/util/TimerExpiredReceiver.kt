package com.example.bottomnav.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.bottomnav.ui.Home
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TimerExpiredReceiver @Inject constructor(
    private val prefUtilInterface: PrefUtilInterface
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        prefUtilInterface.setTimerState(Home.TimerState.Stopped)
        prefUtilInterface.setAlarmSetTime(0)
    }
}