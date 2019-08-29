package id.rezkyauliapratama.fhome.data.source.api

import id.innovation.innovendor.fsample.data.sample.MovieApi
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import io.reactivex.Single

class MovieApiDataSourceImpl(private val api: MovieApi) : MovieApiDataSource {

    override fun getPopularMovies(apiKey: String, pageNum: Int): Single<List<MovieDtoBean>> =
        api.getPopularMovies(apiKey, pageNum)
            .map {
                it.moviesDto
            }

}