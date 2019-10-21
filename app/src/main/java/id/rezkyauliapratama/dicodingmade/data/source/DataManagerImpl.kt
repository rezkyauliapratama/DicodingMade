package id.rezkyauliapratama.dicodingmade.data.source

import id.innovation.libdatabase.entity.FavoriteTable
import id.rezkyauliapratama.dicodingmade.data.DataManager
import id.rezkyauliapratama.dicodingmade.data.entity.MovieDtoBean
import id.rezkyauliapratama.dicodingmade.data.entity.TvShowDtoBean
import id.rezkyauliapratama.dicodingmade.data.entity.favorite.movie.DetailMovieDto
import id.rezkyauliapratama.dicodingmade.data.entity.favorite.tvshow.DetailTvDto
import id.rezkyauliapratama.dicodingmade.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.dicodingmade.data.source.local.MovieLocalDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val apiDataSource: MovieApiDataSource,
    private val localDataSource: MovieLocalDataSource
) : DataManager {

    override fun getReleaseMovie(language: String): Single<List<MovieDtoBean>> {
        return apiDataSource.getReleaseMovie(language)
    }

    override fun getPopularMoviesSearch(
        query: String,
        pageNum: Int,
        language: String
    ): Single<List<MovieDtoBean>> {
        return apiDataSource.getPopularMoviesSearch(query, pageNum, language)
    }

    override fun getTvShowsSearch(
        query: String,
        pageNum: Int,
        language: String
    ): Single<List<TvShowDtoBean>> {
        return apiDataSource.getTvShowsSearch(query, pageNum, language)
    }

    override fun getFavorite(type: Int): Single<List<FavoriteTable>> {
        return localDataSource.getFavorite(type)
    }

    override fun getTvShows(pageNum: Int, language: String): Single<List<TvShowDtoBean>> {
        return apiDataSource.getTvShows(pageNum, language)
    }

    override fun getPopularMovies(pageNum: Int, language: String): Single<List<MovieDtoBean>> =
        apiDataSource.getPopularMovies(pageNum, language)


    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> {
        return apiDataSource.getDetailTvShow(tvShowId, language)
    }

    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> {
        return apiDataSource.getDetailMovie(movieId, language)
    }
}