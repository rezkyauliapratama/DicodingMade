package id.rezkyauliapratama.fhome.ui.popularmovie.viewmodel

import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import timber.log.Timber
import javax.inject.Inject

class PopularMovieViewModel @Inject constructor(
        private val getPopularMovie: PopularMovieUsecase
) : BaseViewModel() {

    internal val moviesList: SingleLiveEvent<Resource<List<PopularMovieResult>>> = SingleLiveEvent()

    override fun loadPage(multipleTimes: Boolean?) {
        super.loadPage(multipleTimes)
        Timber.e("PopularViewModel loadpage")
        moviesList.setLoading()
        getPopularMovie.execute().subscribe(
            {
                moviesList.setSuccess(it)
            },
            {
                moviesList.setError(it)
            }
        ).track()
    }

}
