package id.rezkyauliapratama.fhome.data

import id.innovation.libcore.di.FeatureScope
import id.rezkyauliapratama.fhome.BuildConfig
import id.rezkyauliapratama.fhome.data.entity.mapToMovieDomain
import id.rezkyauliapratama.fhome.data.entity.mapToTvShowDomain
import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import id.rezkyauliapratama.fhome.domain.entity.TvShowModel
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class MovieRepositoryImpl @Inject constructor(
    private val dataManager: DataManager
) : MovieRepository {

    override fun getTvShows(): Single<List<TvShowModel>> {
        return dataManager.getTvShows()
            .map {
                it.mapToTvShowDomain()
            }
    }

    override fun getPopularMovies(pageNum: Int): Single<List<MovieModel>> {
        return dataManager.getPopularMovies(BuildConfig.API_KEY, pageNum)
            .map {
                it.mapToMovieDomain()
            }
    }

}