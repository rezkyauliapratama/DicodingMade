package id.rezkyauliapratama.fhome.domain.repository

import id.rezkyauliapratama.fhome.domain.entity.MovieModel
import id.rezkyauliapratama.fhome.domain.entity.TvShowModel
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies(pageNum: Int): Single<List<MovieModel>>
    fun getTvShows(): Single<List<TvShowModel>>
}