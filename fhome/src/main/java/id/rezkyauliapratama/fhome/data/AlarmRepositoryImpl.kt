package id.rezkyauliapratama.fhome.data

import id.rezkyauliapratama.fhome.domain.repository.AlarmRepository

class AlarmRepositoryImpl(
    private val dataManager: DataManager
) : AlarmRepository {

    override fun setDailyReminder(isActivate: Boolean): Boolean {
        return dataManager.setDailyReminder(isActivate)
    }

    override fun setReleaseReminder(isActivate: Boolean): Boolean {
        return dataManager.setReleaseReminder(isActivate)
    }

    override fun isDailyReminderActivate(): Boolean {
        return dataManager.isDailyReminderActivate()
    }

    override fun isReleaseReminderActivate(): Boolean {
        return dataManager.isReleaseReminderActivate()
    }
}