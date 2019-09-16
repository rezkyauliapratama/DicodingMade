package id.rezkyauliapratama.fhome.data

import android.content.Context
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libdatabase.entity.FavoriteType
import id.rezkyauliapratama.fhome.data.entity.favorite.movie.mapToDomain
import id.rezkyauliapratama.fhome.data.entity.favorite.tvshow.mapToTvShowDomain
import id.rezkyauliapratama.fhome.data.entity.mapToMovieDomain
import id.rezkyauliapratama.fhome.data.entity.mapToTvShowDomain
import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import id.rezkyauliapratama.fhome.domain.entity.TvShowModel
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class MovieRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager
) : MovieRepository {
    override fun getTvShowFavorites(): Single<List<TvShowModel>> {
        return dataManager.getFavorite(FavoriteType.TV_SHOW.code).flatMapObservable {
            Observable.fromIterable(it)
        }.flatMapSingle {
            dataManager.getDetailTvShow(it.itemId.toInt(), LocaleManager.getLanguagePref(context))
                .map {
                    it.mapToTvShowDomain()
                }
        }.toList()
    }

    override fun getMovieFavorites(): Single<List<MovieModel>> {
        return dataManager.getFavorite(FavoriteType.MOVIE.code).flatMapObservable {
            Observable.fromIterable(it)
        }.flatMapSingle {
            dataManager.getDetailMovie(it.itemId.toInt(), LocaleManager.getLanguagePref(context))
                .map {
                    it.mapToDomain()
                }
        }.toList()
    }

    override fun getTvShows(pageNum: Int): Single<List<TvShowModel>> {
        return dataManager.getTvShows(pageNum, LocaleManager.getLanguagePref(context))
            .map {
                it.mapToTvShowDomain()
            }
    }

    override fun getPopularMovies(pageNum: Int): Single<List<MovieModel>> {
        return dataManager.getPopularMovies(pageNum, LocaleManager.getLanguagePref(context))
            .map {
                it.mapToMovieDomain()
            }
    }

}