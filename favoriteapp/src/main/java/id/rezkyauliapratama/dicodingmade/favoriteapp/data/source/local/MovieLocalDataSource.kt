package id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.local

import io.reactivex.Single

interface MovieLocalDataSource {
    fun getFavorite(type: Int): Single<List<String>>
}