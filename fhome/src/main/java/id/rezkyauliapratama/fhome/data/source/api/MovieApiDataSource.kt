package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import io.reactivex.Single


interface MovieApiDataSource {
    fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>>
    fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>>
}