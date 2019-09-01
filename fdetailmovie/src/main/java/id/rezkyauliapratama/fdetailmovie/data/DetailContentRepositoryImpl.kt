package id.rezkyauliapratama.fdetailmovie.data

import android.content.Context
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.FeatureScope
import id.rezkyauliapratama.fdetailmovie.data.entity.movie.mapToDomain
import id.rezkyauliapratama.fdetailmovie.data.entity.tvshow.mapToDomain
import id.rezkyauliapratama.fdetailmovie.domain.entity.DetailContentModel
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DetailContentRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager
) : DetailContentRepository {

    override fun getTvShowMovie(tvShowId: Int): Single<DetailContentModel> {
        return dataManager.getDetailTvShow(tvShowId, LocaleManager.getLanguagePref(context)).map {
            it.mapToDomain()
        }
    }

    override fun getDetailMovie(movieId: Int): Single<DetailContentModel> {
        return dataManager.getDetailMovie(movieId, LocaleManager.getLanguagePref(context)).map {
            it.mapToDomain()
        }
    }

}
