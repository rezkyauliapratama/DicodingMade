package id.rezkyauliapratama.dicodingmade.domain.repository

import id.rezkyauliapratama.dicodingmade.domain.entity.MovieModel
import id.rezkyauliapratama.dicodingmade.domain.entity.TvShowModel
import io.reactivex.Single

interface MovieRepository {
    fun getPopularMovies(pageNum: Int): Single<List<MovieModel>>
    fun getReleaseMovie(): Single<List<MovieModel>>
    fun getTvShows(pageNum: Int): Single<List<TvShowModel>>
    fun getMovieFavorites(): Single<List<MovieModel>>
    fun getTvShowFavorites(): Single<List<TvShowModel>>
    fun getMoviesSearch(pageNum: Int, query: String): Single<List<MovieModel>>
    fun getTvShowSearch(pageNum: Int, query: String): Single<List<TvShowModel>>
}