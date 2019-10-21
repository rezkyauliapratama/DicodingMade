package id.rezkyauliapratama.fhome.ui.tvshow.pagination

import androidx.paging.DataSource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.dicodingmade.domain.usecase.TvShowSearchUseCase
import id.rezkyauliapratama.dicodingmade.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.dicodingmade.presenter.entity.TvShowResult
import io.reactivex.disposables.CompositeDisposable

class TvShowPagingDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val getTvShowUsecase: TvShowUsecase,
    private val getSearchTvUsecase: TvShowSearchUseCase,
    private val query: String
) : DataSource.Factory<Int, TvShowResult>() {

    val pagingSourceLiveData = SingleLiveEvent<TvShowPagingDataSource>()

    override fun create(): DataSource<Int, TvShowResult> {
        val tvShowPagingDataSource =
            TvShowPagingDataSource(compositeDisposable, getTvShowUsecase, getSearchTvUsecase, query)
        pagingSourceLiveData.postValue(tvShowPagingDataSource)
        return tvShowPagingDataSource
    }

}
