package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import io.reactivex.Single


interface MovieApiDataSource {
    fun getPopularMovies(apiKey: String, pageNum: Int): Single<List<MovieDtoBean>>
}