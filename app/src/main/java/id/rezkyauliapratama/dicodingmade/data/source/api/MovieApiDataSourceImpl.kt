package id.rezkyauliapratama.dicodingmade.data.source.api

import id.rezkyauliapratama.dicodingmade.data.api.DetailMovieApi
import id.rezkyauliapratama.dicodingmade.data.api.DetailTvShowApi
import id.rezkyauliapratama.dicodingmade.data.api.MovieApi
import id.rezkyauliapratama.dicodingmade.data.entity.MovieDtoBean
import id.rezkyauliapratama.dicodingmade.data.entity.TvShowDtoBean
import id.rezkyauliapratama.dicodingmade.data.entity.favorite.movie.DetailMovieDto
import id.rezkyauliapratama.dicodingmade.data.entity.favorite.tvshow.DetailTvDto
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

class MovieApiDataSourceImpl(
    private val movieApi: MovieApi,
    private val detailMovieApi: DetailMovieApi,
    private val detailTVShowApi: DetailTvShowApi
) : MovieApiDataSource {

    override fun getReleaseMovie(language: String): Single<List<MovieDtoBean>> {
        val calendar = Calendar.getInstance()
        val indonesianLocale = Locale("in", "ID")
        val sdf = SimpleDateFormat("yyyy-MM-dd", indonesianLocale)

        return movieApi.getReleaseMovies(
            releaseDateGte = sdf.format(calendar.time),
            releaseDateLte = sdf.format(calendar.time)
        ).map {
            it.moviesDto
        }
    }

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

    override fun getPopularMoviesSearch(
        query: String,
        pageNum: Int,
        language: String
    ): Single<List<MovieDtoBean>> =
        movieApi.getMoviesSearch(query = query, language = language, pageNumber = pageNum)
            .map {
                it.moviesDto
            }


    override fun getTvShowsSearch(
        query: String,
        pageNum: Int,
        language: String
    ): Single<List<TvShowDtoBean>> =
        movieApi.getTvShowsSearch(query = query, language = language, pageNumber = pageNum)
            .map {
                it.results
            }

}