package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import id.rezkyauliapratama.fhome.data.entity.favorite.movie.DetailMovieDto
import id.rezkyauliapratama.fhome.data.entity.favorite.tvshow.DetailTvDto
import io.reactivex.Single


interface MovieApiDataSource {
    fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>>
    fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>>
    fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto>
    fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto>

}