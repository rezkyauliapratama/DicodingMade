package id.rezkyauliapratama.fhome.ui

import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel() {

    internal val queryLiveData = SingleLiveEvent<String>()
    internal val changeStateLiveData = SingleLiveEvent<Int>()
    internal val searchMovieLiveData = SingleLiveEvent<String>()
    internal val searchTvShowLiveData = SingleLiveEvent<String>()

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
}