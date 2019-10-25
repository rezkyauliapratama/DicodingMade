package id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.api

import id.rezkyauliapratama.dicodingmade.favoriteapp.data.api.DetailMovieApi
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.api.DetailTvShowApi
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.tvshow.DetailTvDto
import io.reactivex.Single

class MovieApiDataSourceImpl(
    private val detailMovieApi: DetailMovieApi,
    private val detailTVShowApi: DetailTvShowApi
) : MovieApiDataSource {

    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> =
        detailTVShowApi.getDetailTvShow(tvShowId, language = language)


    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> =
        detailMovieApi.getDetailMovie(movieId, language = language)

}