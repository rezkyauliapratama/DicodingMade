package id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.pagination.PopularMoviePagingDataSource
import id.rezkyauliapratama.fhome.ui.popularmovie.pagination.PopularMoviePagingDataSourceFactory
import javax.inject.Inject


class PopularMovieViewModel @Inject constructor(
    getPopularMovie: PopularMovieUsecase
) : BaseViewModel() {

    companion object {
        const val pageSize: Int = 5
    }

    val moviesList: LiveData<PagedList<PopularMovieResult>>

    private val popularMoviePagingDataSourceFactory: PopularMoviePagingDataSourceFactory =
        PopularMoviePagingDataSourceFactory(compositeDisposable, getPopularMovie)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        moviesList = LivePagedListBuilder<Int, PopularMovieResult>(
            popularMoviePagingDataSourceFactory,
            config
        ).build()
    }

    fun getState(): LiveData<Resource<List<PopularMovieResult>>> = Transformations.switchMap(
        popularMoviePagingDataSourceFactory.pagingSourceLiveData,
        PopularMoviePagingDataSource::resources
    )

    fun listIsEmpty(): Boolean {
        return moviesList.value?.isEmpty() ?: true
    }
}
