package id.rezkyauliapratama.fdetailmovie.data.source.local

import id.innovation.libdatabase.entity.FavoriteTable
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface DetailContentLocalDataSource {
    fun getFavorite(itemId: String): Single<FavoriteTable>
    fun setFavorite(favoriteTable: FavoriteTable): Single<Long>
    fun deleteFavorite(itemId: String): Completable
}