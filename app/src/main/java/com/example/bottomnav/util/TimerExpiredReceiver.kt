package com.example.bottomnav.util

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.bottomnav.MainActivity
import com.example.bottomnav.R
import com.example.bottomnav.data.repository.DatabaseRepository
import com.example.bottomnav.home.ui.Home
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TimerExpiredReceiver @Inject constructor(
    private val prefUtilInterface: PrefUtilInterface,
    private val databaseRepository: DatabaseRepository
) : BroadcastReceiver() {

    private val CHANNEL_ID = "timerExpiredChannel"
    private val NOTIFICATION_ID = 1

    override fun onReceive(context: Context, intent: Intent) {

        prefUtilInterface.setTimerState(Home.TimerState.Stopped)
        prefUtilInterface.setAlarmSetTime(0)

        val selectedCategory = prefUtilInterface.getSelectedCategory()
        val duration = prefUtilInterface.getTimerLength() as Long

        //update pomodoroDuration in database
        if(selectedCategory != null){
            GlobalScope.launch(Dispatchers.IO) {
                databaseRepository.addCompletedPomodoroBasedOnCategory(0, selectedCategory, duration)
            }
        }

        //notification
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_circle_notifications_24)
            .setContentTitle("Pomodoro Completed")
            .setContentText("Wow, you're on a roll! Time for a quick break and then back to crushing those Pomodoros like a boss!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }
        else{
            Toast.makeText(context, "Timer has expired", Toast.LENGTH_SHORT).show()
        }
    }
}