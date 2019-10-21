package id.rezkyauliapratama.fhome.ui.popularmovie.pagination

import id.innovation.libcore.domain.pagination.BasePageKeyedDataSource
import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.dicodingmade.domain.usecase.PopularMovieSearchUseCase
import id.rezkyauliapratama.dicodingmade.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.dicodingmade.presenter.entity.PopularMovieResult

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class PopularMoviePagingDataSource(
    compositeDisposable: CompositeDisposable,
    private val getPopularMovie: PopularMovieUsecase,
    private val getSearchUseCase: PopularMovieSearchUseCase,
    private val query: String
) : BasePageKeyedDataSource<Int, PopularMovieResult>(compositeDisposable) {

    var resources: SingleLiveEvent<Resource<List<PopularMovieResult>>> = SingleLiveEvent()

    private val initialPage = 1
    private val adjacentPage = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PopularMovieResult>
    ) {
        resources.setLoading()


        if (query.isEmpty()) {
            loadPopularMovies(initialPage).subscribe(
                { response ->
                    resources.setSuccess(response)
                    callback.onResult(response, null, initialPage + adjacentPage)
                },
                {
                    resources.setError(it)
                }).track()
        } else {
            SearchPopularMovies(initialPage, query).subscribe(
                { response ->
                    resources.setSuccess(response)
                    callback.onResult(response, null, initialPage + adjacentPage)
                },
                {
                    resources.setError(it)
                }).track()
        }

    }

    private fun loadPopularMovies(page: Int): Single<List<PopularMovieResult>> {
        return getPopularMovie.execute(
            mapOf(
                PopularMovieUsecase.pageNum to page
            )
        )
    }


    private fun SearchPopularMovies(page: Int, query: String): Single<List<PopularMovieResult>> {
        Timber.e("SearchPopularMovies : $query")

        return getSearchUseCase.execute(
            mapOf(
                PopularMovieSearchUseCase.query to query,
                PopularMovieSearchUseCase.pageNum to page
            )
        )
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PopularMovieResult>
    ) {
        resources.setLoading()

        if (query.isEmpty()) {
            loadPopularMovies(params.key).subscribe(
                { response ->
                    resources.setSuccess()
                    callback.onResult(response, params.key + adjacentPage)
                },
                {
                    resources.setError(it)
                }
            ).track()
        } else {
            SearchPopularMovies(params.key, query).subscribe(
                { response ->
                    resources.setSuccess(response)
                    callback.onResult(response, params.key + adjacentPage)
                },
                {
                    resources.setError(it)
                }).track()
        }

    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PopularMovieResult>
    ) {

    }
}
