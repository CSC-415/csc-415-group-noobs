package com.example.bottomnav

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bottomnav.achievements.ui.Achievements
import com.example.bottomnav.databinding.ActivityMainBinding
import com.example.bottomnav.home.ui.Home
import com.example.bottomnav.progress.ui.Progress
import com.example.bottomnav.todo.ui.ToDo
import com.example.bottomnav.util.PrefUtil
import com.example.bottomnav.util.TimerExpiredReceiver
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFrag(Home())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFrag(Home())
                R.id.progress -> replaceFrag(Progress())
                R.id.achievements -> replaceFrag(Achievements())
                R.id.to_do -> replaceFrag(ToDo())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFrag(fragment : Fragment) {
        val fragManager  = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frame_layout,fragment)
        fragTransaction.commit()
    }
}