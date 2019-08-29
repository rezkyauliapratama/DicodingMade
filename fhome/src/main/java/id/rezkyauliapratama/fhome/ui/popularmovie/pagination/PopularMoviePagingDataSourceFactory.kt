package id.rezkyauliapratama.fhome.ui.popularmovie.pagination

import androidx.paging.DataSource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import io.reactivex.disposables.CompositeDisposable

class PopularMoviePagingDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val getPopularMovie: PopularMovieUsecase
) : DataSource.Factory<Int, PopularMovieResult>() {

    val pagingSourceLiveData = SingleLiveEvent<PopularMoviePagingDataSource>()

    override fun create(): DataSource<Int, PopularMovieResult> {
        val popularMoviePagingDataSource =
            PopularMoviePagingDataSource(compositeDisposable, getPopularMovie)
        pagingSourceLiveData.postValue(popularMoviePagingDataSource)
        return popularMoviePagingDataSource
    }

}
