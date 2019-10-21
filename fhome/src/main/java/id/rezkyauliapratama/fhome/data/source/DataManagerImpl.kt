package id.rezkyauliapratama.fhome.data.source

import id.rezkyauliapratama.fhome.data.DataManager
import id.rezkyauliapratama.fhome.data.source.local.AlarmLocalDataSource

class DataManagerImpl(
    private val alarmLocalDataSource: AlarmLocalDataSource
) : DataManager {

    override fun setDailyReminder(isActivate: Boolean): Boolean {
        return alarmLocalDataSource.setDailyReminder(isActivate)
    }


    override fun setReleaseReminder(isActivate: Boolean): Boolean {
        return alarmLocalDataSource.setReleaseReminder(isActivate)
    }

    override fun isReleaseReminderActivate(): Boolean {
        return alarmLocalDataSource.isReleaseReminderActivate()
    }

    override fun isDailyReminderActivate(): Boolean {
        return alarmLocalDataSource.isDailyReminderActivate()
    }
}