package id.rezkyauliapratama.fhome.ui.favorite

import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.innovation.libdatabase.entity.FavoriteType
import id.rezkyauliapratama.fhome.domain.usecase.MovieFavoritesUseCase
import id.rezkyauliapratama.fhome.domain.usecase.TvShowFavoritesUseCase
import id.rezkyauliapratama.fhome.ui.entity.PopularMovieResult
import id.rezkyauliapratama.fhome.ui.entity.TvShowResult
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val movieFavoritesUseCase: MovieFavoritesUseCase,
    private val tvShowFavoriteUseCase: TvShowFavoritesUseCase
) : BaseViewModel() {

    override fun loadPage(multipleTimes: Boolean?) {
        super.loadPage(multipleTimes)
        getMovieFavorites()
        getTvShowFavorite()
    }

    internal val movieFavoritesLiveData = SingleLiveEvent<Resource<List<PopularMovieResult>>>()
    internal val tvShowFavoritesLiveData = SingleLiveEvent<Resource<List<TvShowResult>>>()

    private fun getMovieFavorites() {
        movieFavoritesUseCase.execute().doOnSubscribe {
            movieFavoritesLiveData.setLoading()
        }.subscribe(
            {
                movieFavoritesLiveData.setSuccess(it)
            },
            {
                movieFavoritesLiveData.setError(it)
            }
        ).track()
    }

    private fun getTvShowFavorite() {
        tvShowFavoriteUseCase.execute().doOnSubscribe {
            tvShowFavoritesLiveData.setLoading()
        }.subscribe(
            {
                tvShowFavoritesLiveData.setSuccess(it)
            },
            {
                tvShowFavoritesLiveData.setError(it)
            }
        ).track()
    }
}