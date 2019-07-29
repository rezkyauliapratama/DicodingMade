package id.rezkyauliapratama.fhome.data.source.mock

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import io.reactivex.Single


interface MovieMockDataSource {
    fun getPopularMovies(): Single<List<MovieDtoBean>>
}