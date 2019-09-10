package id.rezkyauliapratama.fdetailmovie.data.source.local

import id.innovation.libdatabase.dao.FavoriteDao
import id.innovation.libdatabase.entity.FavoriteTable
import io.reactivex.Maybe
import io.reactivex.Single

class DetailContentLocalDataSourceImpl(
    private val favoriteDao: FavoriteDao
) : DetailContentLocalDataSource {
    override fun getFavorite(itemId: String): Single<FavoriteTable> {
        return favoriteDao.getItemById(itemId)
    }

    override fun setFavorite(favoriteTable: FavoriteTable): Single<Long> {
        return favoriteDao.insert(favoriteTable)
    }

}