package id.rezkyauliapratama.fhome.ui.tvshow.pagination

import androidx.paging.DataSource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import io.reactivex.disposables.CompositeDisposable

class TvShowPagingDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val getTvShowUsecase: TvShowUsecase
) : DataSource.Factory<Int, TvShowResult>() {

    val pagingSourceLiveData = SingleLiveEvent<TvShowPagingDataSource>()

    override fun create(): DataSource<Int, TvShowResult> {
        val tvShowPagingDataSource =
            TvShowPagingDataSource(compositeDisposable, getTvShowUsecase)
        pagingSourceLiveData.postValue(tvShowPagingDataSource)
        return tvShowPagingDataSource
    }

}
