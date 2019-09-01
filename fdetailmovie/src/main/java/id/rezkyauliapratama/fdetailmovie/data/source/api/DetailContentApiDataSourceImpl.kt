package id.rezkyauliapratama.fdetailmovie.data.source.api

import id.rezkyauliapratama.fdetailmovie.data.api.DetailMovieApi
import id.rezkyauliapratama.fdetailmovie.data.api.DetailTvShowApi
import id.rezkyauliapratama.fdetailmovie.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.fdetailmovie.data.entity.tvshow.DetailTvDto
import io.reactivex.Single

class DetailContentApiDataSourceImpl(
    private val detailMovieApi: DetailMovieApi,
    private val detailTvShowApi: DetailTvShowApi
) :
    DetailContentApiDataSource {

    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> =
        detailTvShowApi.getDetailTvShow(tvShowId, language = language)

    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> =
        detailMovieApi.getDetailMovie(movieId, language = language)

}