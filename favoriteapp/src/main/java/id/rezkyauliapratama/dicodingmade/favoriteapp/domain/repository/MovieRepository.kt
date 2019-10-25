package id.rezkyauliapratama.dicodingmade.favoriteapp.domain.repository

import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.entity.MovieModel
import id.rezkyauliapratama.dicodingmade.favoriteapp.domain.entity.TvShowModel
import io.reactivex.Single

interface MovieRepository {
    fun getMovieFavorites(): Single<List<MovieModel>>
    fun getTvShowFavorites(): Single<List<TvShowModel>>
}