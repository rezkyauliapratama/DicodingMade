package id.rezkyauliapratama.dicodingmade.data.source.local

import id.innovation.libdatabase.entity.FavoriteTable
import id.innovation.libdatabase.entity.FavoriteType
import io.reactivex.Single

interface MovieLocalDataSource {
    fun getFavorite(type: Int): Single<List<FavoriteTable>>
}