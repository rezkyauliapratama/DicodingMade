package id.rezkyauliapratama.fhome.data

import id.innovation.libcore.di.FeatureScope
import id.rezkyauliapratama.fhome.data.entity.mapToDomain
import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import id.rezkyauliapratama.fhome.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class MovieRepositoryImpl @Inject constructor(
    private val dataManager: DataManager
) : MovieRepository {

    override fun getPopularMovies(): Single<List<MovieModel>> {
        return dataManager.getPopularMovies()
            .map {
                it.mapToDomain()
            }
    }

}