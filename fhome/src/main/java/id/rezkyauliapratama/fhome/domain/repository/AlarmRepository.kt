package id.rezkyauliapratama.fhome.domain.repository

interface AlarmRepository {
    fun isDailyReminderActivate(): Boolean
    fun isReleaseReminderActivate(): Boolean
    fun setDailyReminder(isActivate: Boolean): Boolean
    fun setReleaseReminder(isActivate: Boolean): Boolean
}