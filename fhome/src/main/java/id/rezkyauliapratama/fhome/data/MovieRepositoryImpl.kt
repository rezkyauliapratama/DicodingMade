package id.rezkyauliapratama.fhome.data

import android.content.Context
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.FeatureScope
import id.rezkyauliapratama.fhome.data.entity.mapToMovieDomain
import id.rezkyauliapratama.fhome.data.entity.mapToTvShowDomain
import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import id.rezkyauliapratama.fhome.domain.entity.TvShowModel
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

@FeatureScope
class MovieRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager
) : MovieRepository {
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