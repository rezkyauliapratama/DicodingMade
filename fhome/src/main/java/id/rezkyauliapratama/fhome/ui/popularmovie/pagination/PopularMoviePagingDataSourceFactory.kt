package id.rezkyauliapratama.fhome.ui.popularmovie.pagination

import androidx.paging.DataSource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.dicodingmade.domain.usecase.PopularMovieSearchUseCase
import id.rezkyauliapratama.dicodingmade.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult
import io.reactivex.disposables.CompositeDisposable

class PopularMoviePagingDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val getPopularMovie: PopularMovieUsecase,
    private val getSearchPopularMovie: PopularMovieSearchUseCase,
    private val query: String
) : DataSource.Factory<Int, PopularMovieResult>() {

    val pagingSourceLiveData = SingleLiveEvent<PopularMoviePagingDataSource>()
    lateinit var popularMoviePagingDataSource: PopularMoviePagingDataSource
    override fun create(): DataSource<Int, PopularMovieResult> {
        popularMoviePagingDataSource =
            PopularMoviePagingDataSource(compositeDisposable, getPopularMovie, getSearchPopularMovie, query)
        pagingSourceLiveData.postValue(popularMoviePagingDataSource)
        return popularMoviePagingDataSource
    }

}
