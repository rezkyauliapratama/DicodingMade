package id.rezkyauliapratama.fhome.data.source.local

interface AlarmLocalDataSource {
    fun isDailyReminderActivate(): Boolean
    fun isReleaseReminderActivate(): Boolean
    fun setDailyReminder(isActivate: Boolean): Boolean
    fun setReleaseReminder(isActivate: Boolean): Boolean
}