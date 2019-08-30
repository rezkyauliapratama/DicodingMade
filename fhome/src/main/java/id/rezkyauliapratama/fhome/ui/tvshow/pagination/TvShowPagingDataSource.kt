package id.rezkyauliapratama.fhome.ui.tvshow.pagination

import id.innovation.libcore.domain.pagination.BasePageKeyedDataSource
import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class TvShowPagingDataSource(
    compositeDisposable: CompositeDisposable,
    private val getTvShowUsecase: TvShowUsecase
) : BasePageKeyedDataSource<Int, TvShowResult>(compositeDisposable) {

    var resources: SingleLiveEvent<Resource<List<TvShowResult>>> = SingleLiveEvent()

    private val initialPage = 1
    private val adjacentPage = 1

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TvShowResult>) {
        resources.setLoading()


        loadTvShows(initialPage).subscribe(
            { response ->
                resources.setSuccess(response)
                callback.onResult(response, null, initialPage + adjacentPage)
            },
            {
                resources.setError(it)
            }).track()
    }

    private fun loadTvShows(page: Int): Single<List<TvShowResult>> {
        return getTvShowUsecase.execute(
            mapOf(
                TvShowUsecase.pageNum to page
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowResult>) {
        resources.setLoading()

        loadTvShows(params.key).subscribe(
            { response ->
                resources.setSuccess(response)
                callback.onResult(response, params.key + adjacentPage)
            },
            {
                resources.setError(it)
            }
        ).track()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowResult>) {

    }
}
