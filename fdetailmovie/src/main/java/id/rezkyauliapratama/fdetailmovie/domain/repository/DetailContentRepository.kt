package id.rezkyauliapratama.fdetailmovie.domain.repository

import id.innovation.libdatabase.entity.FavoriteTable
import id.innovation.libdatabase.entity.FavoriteType
import id.rezkyauliapratama.fdetailmovie.domain.entity.DetailContentModel
import io.reactivex.Maybe
import io.reactivex.Single

interface DetailContentRepository {
    fun getDetailMovie(movieId: Int): Single<DetailContentModel>
    fun getTvShowMovie(tvShowId: Int): Single<DetailContentModel>
    fun getFavorite(itemId: String): Single<FavoriteTable>
    fun setFavorite(itemId: String, favoriteType: FavoriteType): Single<Boolean>

}