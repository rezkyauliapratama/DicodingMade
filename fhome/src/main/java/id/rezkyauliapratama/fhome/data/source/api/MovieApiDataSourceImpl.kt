package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.api.MovieApi
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import io.reactivex.Single

class MovieApiDataSourceImpl(private val api: MovieApi) : MovieApiDataSource {

    override fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>> =
        api.getTvShows(pageNumber = pageNum, language = language)
            .map {
                it.results
            }

    override fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>> =
        api.getMovies(pageNumber = pageNum, language = language)
            .map {
                it.moviesDto
            }

}