package id.rezkyauliapratama.fhome.ui

import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.DailyReminderUsecase
import id.rezkyauliapratama.fhome.domain.usecase.ReleaseReminderUsecase
import id.rezkyauliapratama.fhome.domain.usecase.SetDailyReminderUsecase
import id.rezkyauliapratama.fhome.domain.usecase.SetReleaseReminderUsecase
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val dailyReminderUsecase: DailyReminderUsecase,
    private val releaseReminderUsecase: ReleaseReminderUsecase,
    private val setDailyReminderUsecase: SetDailyReminderUsecase,
    private val setReleaseReminderUsecase: SetReleaseReminderUsecase
) : BaseViewModel() {

    internal val queryLiveData = SingleLiveEvent<String>()
    internal val changeStateLiveData = SingleLiveEvent<Int>()
    internal val searchMovieLiveData = SingleLiveEvent<String>()
    internal val searchTvShowLiveData = SingleLiveEvent<String>()
    internal val dailyReminderLiveData = SingleLiveEvent<Boolean>()
    internal val releaseReminderLiveData = SingleLiveEvent<Boolean>()

    init {
        getStatusReleaseReminder()
        getStatusDailyReminder()
    }

    fun setQuery(query: String) {
        Timber.e("query : $query")
        queryLiveData.value = query
    }

    fun setSearchView(visibility: Int) {
        changeStateLiveData.value = visibility
    }

    fun setSearchMovie(search: String) {
        searchMovieLiveData.value = search
    }

    fun setSearchTvShow(search: String) {
        searchTvShowLiveData.value = search
    }


    private fun getStatusDailyReminder() {
        dailyReminderUsecase.execute().subscribe(
            {
                Timber.e("getStatusDailyReminder success $it")
                dailyReminderLiveData.value = it
            },
            {
                Timber.e("getStatusDailyReminder error $it")
            }
        ).track()
    }

    private fun getStatusReleaseReminder() {
        releaseReminderUsecase.execute().subscribe(
            {
                Timber.e("getStatusReleaseReminder success $it")
                releaseReminderLiveData.value = it
            },
            {
                Timber.e("getStatusReleaseReminder error $it")
            }
        ).track()
    }

    fun setStatusDailyReminder(isActivate: Boolean) {
        Timber.e("setStatusDailyReminder : $isActivate")
        setDailyReminderUsecase.execute(
            mapOf(
                SetDailyReminderUsecase.IS_ACTIVATE to isActivate
            )
        ).subscribe(
            {
                dailyReminderLiveData.value = it
            },
            {
                Timber.e("setStatusDailyReminder error $it")
            }
        ).track()
    }

    fun setStatusReleaseReminder(isActivate: Boolean) {
        setReleaseReminderUsecase.execute(
            mapOf(
                SetReleaseReminderUsecase.IS_ACTIVATE to isActivate
            )
        ).subscribe(
            {
                releaseReminderLiveData.value = it
            },
            {
                Timber.e("setStatusReleaseReminder error $it")
            }
        ).track()
    }
}