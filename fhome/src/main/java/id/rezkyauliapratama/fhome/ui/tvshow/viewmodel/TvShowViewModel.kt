package id.rezkyauliapratama.fhome.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.rezkyauliapratama.fhome.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import id.rezkyauliapratama.fhome.ui.tvshow.pagination.TvShowPagingDataSource
import id.rezkyauliapratama.fhome.ui.tvshow.pagination.TvShowPagingDataSourceFactory
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
    private val getTvShowUseCase: TvShowUsecase
) : BaseViewModel() {


    companion object {
        const val pageSize: Int = 5
    }

    val tvShowList: LiveData<PagedList<TvShowResult>>
    private val tvShowPagingDataSourceFactory: TvShowPagingDataSourceFactory =
        TvShowPagingDataSourceFactory(compositeDisposable, getTvShowUseCase)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        tvShowList = LivePagedListBuilder<Int, TvShowResult>(
            tvShowPagingDataSourceFactory,
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
}
