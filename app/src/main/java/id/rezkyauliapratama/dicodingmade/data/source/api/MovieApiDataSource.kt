package id.rezkyauliapratama.dicodingmade.data.source.api

import id.rezkyauliapratama.dicodingmade.data.entity.MovieDtoBean
import id.rezkyauliapratama.dicodingmade.data.entity.TvShowDtoBean
import id.rezkyauliapratama.dicodingmade.data.entity.favorite.movie.DetailMovieDto
import id.rezkyauliapratama.dicodingmade.data.entity.favorite.tvshow.DetailTvDto
import io.reactivex.Single


interface MovieApiDataSource {
    fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>>
    fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>>
    fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto>
    fun getReleaseMovie(language: String): Single<List<MovieDtoBean>>
    fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto>
    fun getPopularMoviesSearch(
        query: String, pageNum: Int,
        language: String
    ): Single<List<MovieDtoBean>>

    fun getTvShowsSearch(
        query: String, pageNum: Int,
        language: String
    ): Single<List<TvShowDtoBean>>

}