package id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieSearchUseCase
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.popularmovie.pagination.PopularMoviePagingDataSource
import id.rezkyauliapratama.fhome.ui.popularmovie.pagination.PopularMoviePagingDataSourceFactory
import timber.log.Timber
import javax.inject.Inject


class PopularMovieViewModel @Inject constructor(
    private val getPopularMovie: PopularMovieUsecase,
    private val getSearchUseCase: PopularMovieSearchUseCase
) : BaseViewModel() {

    companion object {
        const val pageSize: Int = 5
    }

    var moviesList: LiveData<PagedList<PopularMovieResult>>
    val config: PagedList.Config

    private lateinit var popularMoviePagingDataSourceFactory: PopularMoviePagingDataSourceFactory

    init {
        config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        moviesList = LivePagedListBuilder<Int, PopularMovieResult>(
            getPopularMoviePagingDataSourceFactory(""),
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

    fun setQuery(query: String) {
        Timber.e("setQuery : $query")
        moviesList = LivePagedListBuilder<Int, PopularMovieResult>(
            getPopularMoviePagingDataSourceFactory(query),
            config
        ).build()

    }

    private fun getPopularMoviePagingDataSourceFactory(searchQuery: String): PopularMoviePagingDataSourceFactory {
        popularMoviePagingDataSourceFactory = PopularMoviePagingDataSourceFactory(
            compositeDisposable,
            getPopularMovie,
            getSearchUseCase,
            searchQuery
        )
        return popularMoviePagingDataSourceFactory
    }

}
