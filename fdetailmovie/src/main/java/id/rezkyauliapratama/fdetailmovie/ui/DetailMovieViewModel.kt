package id.rezkyauliapratama.fdetailmovie.ui

import android.os.Bundle
import androidx.room.EmptyResultSetException
import id.innovation.libcore.ui.common.setError
import id.innovation.libcore.ui.common.setLoading
import id.innovation.libcore.ui.common.setSuccess
import id.innovation.libcore.ui.presenterstate.Resource
import id.innovation.libcore.ui.viewmodels.BaseViewModel
import id.innovation.libcore.ui.viewmodels.SingleLiveEvent
import id.innovation.libdatabase.entity.FavoriteType
import id.innovation.libsharedata.entity.DetailMovieResult
import id.rezkyauliapratama.fdetailmovie.domain.usecase.DetailMovieUseCase
import id.rezkyauliapratama.fdetailmovie.domain.usecase.DetailTvShowUseCase
import id.rezkyauliapratama.fdetailmovie.domain.usecase.GetFavoriteUseCase
import id.rezkyauliapratama.fdetailmovie.domain.usecase.SetFavoriteUseCase
import timber.log.Timber
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val detailMovieUseCase: DetailMovieUseCase,
    private val detailTvShowUseCase: DetailTvShowUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
) : BaseViewModel() {

    companion object {
        const val BUNDLE_KEY = "bundle_key"
    }

    internal val detailMovieLiveData = SingleLiveEvent<Resource<DetailMovieResult>>()
    internal val isFavoriteLiveData = SingleLiveEvent<Boolean>()
    private var detailMovieResult: DetailMovieResult? = null

    private lateinit var favoriteType: FavoriteType
    private  var itemId: Int = 0

    fun getDetailMovieResult(movieId: Int) {
        itemId = movieId
        favoriteType = FavoriteType.MOVIE

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
        itemId = tvShowId
        favoriteType = FavoriteType.TV_SHOW

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

    fun getFavorite(id: Int) {
        getFavoriteUseCase.execute(
            mapOf(
                GetFavoriteUseCase.movieId to id
            )
        ).subscribe(
            {
                if (it.itemId == id.toString()){
                    isFavoriteLiveData.value = true
                }
            },
            {
                if (it is EmptyResultSetException){
                    isFavoriteLiveData.value = false
                }
            }
        ).track()
    }

    fun setFavorite() {
        setFavoriteUseCase.execute(
            mapOf(
                SetFavoriteUseCase.itemId to itemId,
                SetFavoriteUseCase.itemType to favoriteType
            )
        ).subscribe (
            {
                Timber.e("success $it")
                isFavoriteLiveData.value = it
            },
            {
                Timber.e("error : $it")
            }
        ).track()
    }

    internal fun saveInstanceState(saveInstance: Bundle) {
        saveInstance.putParcelable(BUNDLE_KEY, detailMovieResult)
    }

    internal fun restoreInstanceState(saveInstance: Bundle?) {
        this.detailMovieResult = saveInstance?.getParcelable(BUNDLE_KEY) as DetailMovieResult
        detailMovieResult?.apply { detailMovieLiveData.setSuccess(detailMovieResult!!) }
    }

}