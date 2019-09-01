package id.rezkyauliapratama.fdetailmovie.data.source.api

import id.rezkyauliapratama.fdetailmovie.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.fdetailmovie.data.entity.tvshow.DetailTvDto
import io.reactivex.Single


interface DetailContentApiDataSource {
    fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto>
    fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto>
}