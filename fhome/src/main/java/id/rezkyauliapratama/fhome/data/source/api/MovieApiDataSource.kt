package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import io.reactivex.Single


interface MovieApiDataSource {
    fun getPopularMovies(apiKey: String, pageNum: Int): Single<List<MovieDtoBean>>
    fun getTvShows(apiKey: String, pageNum: Int): Single<List<TvShowDtoBean>>
}