package id.rezkyauliapratama.fdetailmovie.ui

import android.os.Bundle
import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.domain.usecase.DetailMovieUseCase
import id.rezkyauliapratama.fdetailmovie.domain.usecase.DetailTvShowUseCase
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val detailMovieUseCase: DetailMovieUseCase,
    private val detailTvShowUseCase: DetailTvShowUseCase
) : BaseViewModel() {

    companion object {
        const val BUNDLE_KEY = "bundle_key"
    }

    internal val detailMovieLiveData = SingleLiveEvent<Resource<DetailMovieResult>>()
    private var detailMovieResult: DetailMovieResult? = null

    fun getDetailMovieResult(movieId: Int) {
        detailMovieLiveData.setLoading()

        detailMovieUseCase.execute(
            mapOf(
                DetailMovieUseCase.movieId to movieId
            )
        ).subscribe(
            {
                detailMovieResult = it
                detailMovieLiveData.setSuccess(it)
            },
            {
                detailMovieLiveData.setError(it)
            }
        ).track()
    }

    fun getDetailTvShowResult(tvShowId: Int) {
        detailMovieLiveData.setLoading()

        detailTvShowUseCase.execute(
            mapOf(
                DetailTvShowUseCase.tvShowId to tvShowId
            )
        ).subscribe(
            {
                detailMovieResult = it
                detailMovieLiveData.setSuccess(it)
            },
            {
                detailMovieLiveData.setError(it)
            }
        ).track()
    }

    internal fun saveInstanceState(saveInstance: Bundle){
        saveInstance.putParcelable(BUNDLE_KEY, detailMovieResult)
    }

    internal fun restoreInstanceState(saveInstance: Bundle?) {
        this.detailMovieResult = saveInstance?.getParcelable(BUNDLE_KEY) as DetailMovieResult
        detailMovieResult?.apply { detailMovieLiveData.setSuccess(detailMovieResult!!) }
    }

}