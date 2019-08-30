package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.api.MovieApi
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import io.reactivex.Single

class MovieApiDataSourceImpl(private val api: MovieApi) : MovieApiDataSource {

    override fun getTvShows(apiKey: String, pageNum: Int): Single<List<TvShowDtoBean>> =
        api.getTvShows(apiKey, pageNum)
            .map {
                it.results
            }

    override fun getPopularMovies(apiKey: String, pageNum: Int): Single<List<MovieDtoBean>> =
        api.getMovies(apiKey, pageNum)
            .map {
                it.moviesDto
            }

}