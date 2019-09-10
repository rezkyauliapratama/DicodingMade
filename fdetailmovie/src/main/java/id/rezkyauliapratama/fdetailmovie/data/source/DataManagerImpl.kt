package id.rezkyauliapratama.fdetailmovie.data.source

import id.innovation.libdatabase.entity.FavoriteTable
import id.rezkyauliapratama.fdetailmovie.data.DataManager
import id.rezkyauliapratama.fdetailmovie.data.entity.movie.DetailMovieDto
import id.rezkyauliapratama.fdetailmovie.data.entity.tvshow.DetailTvDto
import id.rezkyauliapratama.fdetailmovie.data.source.api.DetailContentApiDataSource
import id.rezkyauliapratama.fdetailmovie.data.source.local.DetailContentLocalDataSource
import io.reactivex.Maybe
import io.reactivex.Single

class DataManagerImpl(
    private val apiDataSource: DetailContentApiDataSource,
    private val localDataSource: DetailContentLocalDataSource
) : DataManager {

    override fun getFavorite(itemId: String): Single<FavoriteTable> {
        return localDataSource.getFavorite(itemId)
    }

    override fun setFavorite(favoriteTable: FavoriteTable): Single<Long> {
        return localDataSource.setFavorite(favoriteTable)
    }

    override fun getDetailTvShow(tvShowId: Int, language: String): Single<DetailTvDto> {
        return apiDataSource.getDetailTvShow(tvShowId, language)
    }

    override fun getDetailMovie(movieId: Int, language: String): Single<DetailMovieDto> {
        return apiDataSource.getDetailMovie(movieId, language)
    }
}