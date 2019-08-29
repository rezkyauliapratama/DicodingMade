package id.rezkyauliapratama.fhome.ui.popularmovie.pagination

import id.innovation.libcore.domain.pagination.BasePageKeyedDataSource
import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class PopularMoviePagingDataSource(
    compositeDisposable: CompositeDisposable,
    private val getPopularMovie: PopularMovieUsecase
) : BasePageKeyedDataSource<Int, PopularMovieResult>(compositeDisposable) {

    var resources: SingleLiveEvent<Resource<List<PopularMovieResult>>> = SingleLiveEvent()

    private val initialPage = 1
    private val adjacentPage = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PopularMovieResult>) {
        resources.setLoading()


        loadPopularMovies(initialPage).subscribe(
            { response ->
                resources.setSuccess(response)
                callback.onResult(response, null, initialPage + adjacentPage)
            },
            {
                resources.setError(it)
            }).track()
    }

    private fun loadPopularMovies(page: Int): Single<List<PopularMovieResult>> {
        return getPopularMovie.execute(
            mapOf(
                PopularMovieUsecase.pageNum to page
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PopularMovieResult>) {
        resources.setLoading()

        loadPopularMovies(params.key).subscribe(
            { response ->
                resources.setSuccess(response)
                callback.onResult(response, params.key + adjacentPage)
            },
            {
                resources.setError(it)
            }
        ).track()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PopularMovieResult>) {

    }
}
