package id.rezkyauliapratama.dicodingmade.data.source.local

import id.innovation.libdatabase.dao.FavoriteDao
import id.innovation.libdatabase.entity.FavoriteTable
import io.reactivex.Single

class MovieLocalDataSourceImpl(private val favoriteDao: FavoriteDao) : MovieLocalDataSource {

    override fun getFavorite(type: Int): Single<List<FavoriteTable>> {
        return favoriteDao.getItemByType(type)
    }
}