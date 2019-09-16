package id.rezkyauliapratama.fhome.data.source.api

import id.rezkyauliapratama.fhome.data.api.DetailMovieApi
import id.rezkyauliapratama.fhome.data.api.DetailTvShowApi
import id.rezkyauliapratama.fhome.data.api.MovieApi
import id.rezkyauliapratama.fhome.data.entity.MovieDtoBean
import id.rezkyauliapratama.fhome.data.entity.TvShowDtoBean
import id.rezkyauliapratama.fhome.data.entity.favorite.movie.DetailMovieDto
import id.rezkyauliapratama.fhome.data.entity.favorite.tvshow.DetailTvDto
import io.reactivex.Single

class MovieApiDataSourceImpl(
    private val movieApi: MovieApi,
    private val detailMovieApi: DetailMovieApi,
    private val detailTVShowApi: DetailTvShowApi
) : MovieApiDataSource {


    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> =
        detailTVShowApi.getDetailTvShow(tvShowId, language = language)


    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> =
        detailMovieApi.getDetailMovie(movieId, language = language)

    override fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>> =
        movieApi.getTvShows(pageNumber = pageNum, language = language)
            .map {
                it.results
            }

    override fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>> =
        movieApi.getMovies(pageNumber = pageNum, language = language)
            .map {
                it.moviesDto
            }

}