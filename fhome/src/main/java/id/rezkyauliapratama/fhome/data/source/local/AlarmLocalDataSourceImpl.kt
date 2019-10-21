package id.rezkyauliapratama.fhome.data.source.local

import id.innovation.libcore.data.executors.SharedPref
import timber.log.Timber

class AlarmLocalDataSourceImpl(
    private val sharedPref: SharedPref
) : AlarmLocalDataSource {

    override fun setDailyReminder(isActivate: Boolean): Boolean {
        Timber.e("setDailyReminder $isActivate")
        sharedPref.dailyReminder = isActivate
        return sharedPref.dailyReminder
    }

    override fun setReleaseReminder(isActivate: Boolean): Boolean {
        Timber.e("setReleaseReminder $isActivate")
        sharedPref.releaseReminder = isActivate
        return sharedPref.releaseReminder
    }

    override fun isDailyReminderActivate(): Boolean {
        return sharedPref.dailyReminder
    }

    override fun isReleaseReminderActivate(): Boolean {
        return sharedPref.releaseReminder
    }
}