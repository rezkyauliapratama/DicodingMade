package id.rezkyauliapratama.fdetailmovie.ui

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

    internal val detailMovieLiveData = SingleLiveEvent<Resource<DetailMovieResult>>()

    fun getDetailMovieResult(movieId: Int) {
        detailMovieLiveData.setLoading()

        detailMovieUseCase.execute(
            mapOf(
                DetailMovieUseCase.movieId to movieId
            )
        ).subscribe(
            {
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
                detailMovieLiveData.setSuccess(it)
            },
            {
                detailMovieLiveData.setError(it)
            }
        ).track()
    }
}