package id.rezkyauliapratama.dicodingmade.favoriteapp.data.source

import id.rezkyauliapratama.dicodingmade.favoriteapp.data.DataManager
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.entity.tvshow.DetailTvDto
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.api.MovieApiDataSource
import id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.local.MovieLocalDataSource
import io.reactivex.Single

class DataManagerImpl(
    private val apiDataSource: MovieApiDataSource,
    private val localDataSource: MovieLocalDataSource
) : DataManager {

    override fun getFavorite(type: Int): Single<List<String>> {
        return localDataSource.getFavorite(type)
    }

    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> {
        return apiDataSource.getDetailTvShow(tvShowId, language)
    }

    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> {
        return apiDataSource.getDetailMovie(movieId, language)
    }
}