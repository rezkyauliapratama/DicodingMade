package id.rezkyauliapratama.dicodingmade.service

import android.app.AlarmManager
import android.app.IntentService
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.Nullable
import timber.log.Timber
import java.util.*
import java.util.Calendar.*


class AlarmService : IntentService("AlarmService") {

    companion object {
        private const val ALARM_REQUEST_CODE_REMINDER = 1001
        private const val ALARM_REQUEST_CODE_FAVORITE_MOVIE = 1002
        const val RELEASE_REMINDER_ACTIVE = "RELEASE_REMINDER_ACTIVE"
        const val DAILY_REMINDER_ACTIVE = "DAILY_REMINDER_ACTIVE"
        const val BUNDLE_TYPE = "ALARM_TYPE"
        const val TYPE_REMINDER = "reminder"
        const val TYPE_RELEASE_TODAY = "release_today"

        fun cancelDailyReminder(context: Context) {
            val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmBroadCastReceiver::class.java)
            intent.putExtra(BUNDLE_TYPE, TYPE_REMINDER)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                ALARM_REQUEST_CODE_REMINDER,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            alarmManager.cancel(pendingIntent)
        }

        fun cancelReleaseReminder(context: Context) {
            val alarmManager: AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmBroadCastReceiver::class.java)
            intent.putExtra(BUNDLE_TYPE, TYPE_RELEASE_TODAY)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                ALARM_REQUEST_CODE_FAVORITE_MOVIE,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            alarmManager.cancel(pendingIntent)
        }
    }

    override fun onHandleIntent(@Nullable intent: Intent?) {

        val isDailyReminderActive = intent?.getBooleanExtra(DAILY_REMINDER_ACTIVE, false) ?: false
        val isReleaseReminderActive = intent?.getBooleanExtra(RELEASE_REMINDER_ACTIVE, false) ?: false
        Timber.e("onhandleIntent RELEASE_REMINDER_ACTIVE : ${isReleaseReminderActive}")
        Timber.e("onhandleIntent DAILY_REMINDER_ACTIVE : ${isDailyReminderActive}")

        if (isDailyReminderActive)
            setAlarmReminder()

        if (isReleaseReminderActive)
            setAlarmRelease()
    }

    private fun getIntent(
        context: Context,
        valueRequestCode: Int,
        valueName: String
    ): PendingIntent {
        val intent = Intent(context, AlarmBroadCastReceiver::class.java)
        intent.putExtra(BUNDLE_TYPE, valueName)
        return PendingIntent.getBroadcast(
            context,
            valueRequestCode,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    private fun setAlarmReminder() {
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent: PendingIntent =
            getIntent(this, ALARM_REQUEST_CODE_REMINDER, TYPE_REMINDER)

        val datetimeToAlarm = getInstance(Locale.getDefault())
        datetimeToAlarm.timeInMillis = System.currentTimeMillis()
        datetimeToAlarm.set(HOUR_OF_DAY, 7)
        datetimeToAlarm.set(MINUTE, 0)
        datetimeToAlarm.set(SECOND, 0)
        datetimeToAlarm.set(MILLISECOND, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            datetimeToAlarm.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent
        )
    }

    private fun setAlarmRelease() {
        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent: PendingIntent =
            getIntent(this, ALARM_REQUEST_CODE_FAVORITE_MOVIE, TYPE_RELEASE_TODAY)

        val datetimeToAlarm = getInstance(Locale.getDefault())
        datetimeToAlarm.timeInMillis = System.currentTimeMillis()
        datetimeToAlarm.set(HOUR_OF_DAY, 8)
        datetimeToAlarm.set(MINUTE, 0)
        datetimeToAlarm.set(SECOND, 0)
        datetimeToAlarm.set(MILLISECOND, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            datetimeToAlarm.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent
        )
    }
}