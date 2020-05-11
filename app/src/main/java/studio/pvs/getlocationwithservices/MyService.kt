package studio.pvs.getlocationwithservices

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.widget.Toast

class MyService : Service() {

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(this, MyReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, intent, 0)
        }

        startAlarm()

    }

    private fun startAlarm(){
//        alarmMgr?.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60 * 1000, alarmIntent)
        alarmMgr?.setRepeating(AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),1000 * 60 * 1,alarmIntent)
    }

    private fun cancelAlarm(){
        if (alarmMgr != null) {
            alarmMgr!!.cancel(alarmIntent)
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show()
        }
    }

}
