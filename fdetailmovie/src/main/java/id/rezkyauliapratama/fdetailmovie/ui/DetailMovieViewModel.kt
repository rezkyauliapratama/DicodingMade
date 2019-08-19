package id.rezkyauliapratama.fdetailmovie.ui

import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.innovation.libsharedata.entity.DetailMovieResult
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor() : BaseViewModel() {

    internal val detailMovieLiveData = SingleLiveEvent<DetailMovieResult>()

    fun getDetailMovieResult(detailMovieResult: DetailMovieResult) {
        detailMovieLiveData.value = detailMovieResult
    }
}