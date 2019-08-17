package id.rezkyauliapratama.fhome.ui.tvshow.viewmodel

import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.rezkyauliapratama.fhome.domain.usecase.PopularMovieUsecase
import id.rezkyauliapratama.fhome.domain.usecase.TvShowUsecase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import timber.log.Timber
import javax.inject.Inject

class TvShowViewModel @Inject constructor(
        private val getTvShowUseCase: TvShowUsecase
) : BaseViewModel() {

    internal val tvShowLiveData: SingleLiveEvent<Resource<List<TvShowResult>>> = SingleLiveEvent()

    override fun loadPage(multipleTimes: Boolean?) {
        super.loadPage(multipleTimes)
        tvShowLiveData.setLoading()
        getTvShowUseCase.execute().subscribe(
            {
                tvShowLiveData.setSuccess(it)
            },
            {
                tvShowLiveData.setError(it)
            }
        ).track()
    }

}
