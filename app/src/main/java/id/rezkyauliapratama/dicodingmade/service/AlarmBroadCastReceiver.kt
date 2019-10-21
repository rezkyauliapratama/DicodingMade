package id.rezkyauliapratama.dicodingmade.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import id.innovation.libcore.data.executors.SharedPref
import id.innovation.libcore.di.helper.CoreInjectHelper
import id.rezkyauliapratama.dicodingmade.R
import id.rezkyauliapratama.dicodingmade.di.DaggerFeatureComponent
import id.rezkyauliapratama.dicodingmade.domain.repository.MovieRepository
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class AlarmBroadCastReceiver : BroadcastReceiver() {

    var countId = 0

    @Inject
    lateinit var movieRepository: MovieRepository

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onReceive(context: Context, intent: Intent) {
        DaggerFeatureComponent
            .builder()
            .coreComponent(
                CoreInjectHelper.provideCoreComponent(
                    context.applicationContext
                )
            )
            .build()
            .inject(this)

        countId = 0
        Timber.e("intent : ${intent}")
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            val serviceIntent = Intent(context, AlarmService::class.java)

            serviceIntent.putExtra(AlarmService.DAILY_REMINDER_ACTIVE, sharedPref.dailyReminder)
            serviceIntent.putExtra(AlarmService.RELEASE_REMINDER_ACTIVE, sharedPref.releaseReminder)

            context.startService(serviceIntent)
        } else {
            val type = intent.getStringExtra(AlarmService.BUNDLE_TYPE)
            if (type == AlarmService.TYPE_REMINDER)
                notificationDialogReminder(context, type)
            else{
                val listViewModel = movieRepository.getReleaseMovie().subscribeOn(Schedulers.io()).blockingGet()
                listViewModel.map {
                    Timber.e("movieModel : $it")
                    notificationDialogRelease(context, type, it.originalTitle)
                }
            }
        }
    }

    private fun notificationDialogReminder(context: Context, type:String) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val NOTIFICATION_CHANNEL_ID = "Reminder_$type"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") val notificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.catalog_movie),
                    NotificationManager.IMPORTANCE_MAX
                )
            // Configure the notification channel.


            notificationChannel.description = context.getString(R.string.catalog_movie)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = context.getColor(R.color.colorAccent)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_tab_movie)
            .setTicker(context.getString(R.string.reminder_movie))
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle(context.getString(R.string.reminder_movie))
            .setContentText(context.getString(R.string.description_reminder))
            .setContentInfo("Information")
        notificationManager.notify(1001, notificationBuilder.build())
    }

    private fun notificationDialogRelease(context: Context, type:String, movieName: String) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val NOTIFICATION_CHANNEL_ID = "Reminder_$type"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") val notificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.catalog_movie),
                    NotificationManager.IMPORTANCE_MAX
                )
            // Configure the notification channel.


            notificationChannel.description = context.getString(R.string.catalog_movie)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = context.getColor(R.color.colorAccent)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setAutoCancel(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.ic_tab_movie)
            .setTicker(context.getString(R.string.catalog_movie))
            //.setPriority(Notification.PRIORITY_MAX)
            .setContentTitle(movieName)
            .setContentText("$movieName ${context.getString(R.string.has_been_release_today)}")
            .setContentInfo("Information")
        notificationManager.notify(countId++, notificationBuilder.build())
    }
}