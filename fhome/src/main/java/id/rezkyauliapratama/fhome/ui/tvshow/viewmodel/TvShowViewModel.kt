package id.rezkyauliapratama.fhome.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.rezkyauliapratama.dicodingmade.domain.usecase.TvShowSearchUseCase
import id.rezkyauliapratama.dicodingmade.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import id.rezkyauliapratama.fhome.ui.tvshow.pagination.TvShowPagingDataSource
import id.rezkyauliapratama.fhome.ui.tvshow.pagination.TvShowPagingDataSourceFactory
import timber.log.Timber
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
    private val getTvShowUseCase: TvShowUsecase,
    private val getSearchUseCase: TvShowSearchUseCase
) : BaseViewModel() {


    companion object {
        const val pageSize: Int = 5
    }

    var tvShowList: LiveData<PagedList<TvShowResult>>
    val config: PagedList.Config

    private lateinit var tvShowPagingDataSourceFactory: TvShowPagingDataSourceFactory

    init {
        config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        tvShowList = LivePagedListBuilder<Int, TvShowResult>(
            getTvShowPagingDataSourceFactory(""),
            config
        ).build()
    }

    fun getState(): LiveData<Resource<List<TvShowResult>>> = Transformations.switchMap(
        tvShowPagingDataSourceFactory.pagingSourceLiveData,
        TvShowPagingDataSource::resources
    )

    fun listIsEmpty(): Boolean {
        return tvShowList.value?.isEmpty() ?: true
    }

    fun setQuery(query: String) {
        Timber.e("setQuery : $query")
        tvShowList = LivePagedListBuilder<Int, TvShowResult>(
            getTvShowPagingDataSourceFactory(query),
            config
        ).build()

    }

    private fun getTvShowPagingDataSourceFactory(searchQuery: String): TvShowPagingDataSourceFactory {
        tvShowPagingDataSourceFactory =
            TvShowPagingDataSourceFactory(
                compositeDisposable,
                getTvShowUseCase,
                getSearchUseCase,
                searchQuery
            )
        return tvShowPagingDataSourceFactory
    }
}
