package id.rezkyauliapratama.fhome.domain.repository

import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies(): Single<List<MovieModel>>
    fun getTvShows(): Single<List<MovieModel>>
}