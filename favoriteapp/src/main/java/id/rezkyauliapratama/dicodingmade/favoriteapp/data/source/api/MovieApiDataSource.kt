package id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.api

import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.tvshow.DetailTvDto
import io.reactivex.Single


interface MovieApiDataSource {
    fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto>
    fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto>
}