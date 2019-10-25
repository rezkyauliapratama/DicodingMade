package id.rezkyauliapratama.dicodingmade.favoriteapp.data

import android.content.Context
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.annotation.FeatureScope
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.movie.mapToDomain
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.tvshow.mapToTvShowDomain
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.entity.MovieModel
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.entity.TvShowModel
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

@FeatureScope
class MovieRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager
) : MovieRepository {


    override fun getTvShowFavorites(): Single<List<TvShowModel>> {
        return dataManager.getFavorite(1).flatMapObservable {
            Observable.fromIterable(it)
        }.flatMapSingle {
            dataManager.getDetailTvShow(it.toInt(), LocaleManager.getLanguagePref(context))
                .map {
                    it.mapToTvShowDomain()
                }
        }.toList()
    }

    override fun getMovieFavorites(): Single<List<MovieModel>> {
        Timber.e("getMovieFavorites")
        return dataManager.getFavorite(0).flatMapObservable {
            Observable.fromIterable(it)
        }.flatMapSingle {
            Timber.e("id : $it")

            dataManager.getDetailMovie(it.toInt(), LocaleManager.getLanguagePref(context))
                .map {
                    it.mapToDomain()
                }
        }.toList()
    }
}