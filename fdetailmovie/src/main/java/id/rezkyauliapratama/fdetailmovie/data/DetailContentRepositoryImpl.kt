package id.rezkyauliapratama.fdetailmovie.data

import android.content.Context
import androidx.room.EmptyResultSetException
import id.innovation.libcore.data.locale.LocaleManager
import id.innovation.libcore.di.annotation.FeatureScope
import id.innovation.libdatabase.entity.FavoriteTable
import id.innovation.libdatabase.entity.FavoriteType
import id.rezkyauliapratama.fdetailmovie.data.entity.movie.mapToDomain
import id.rezkyauliapratama.fdetailmovie.data.entity.tvshow.mapToDomain
import id.rezkyauliapratama.fdetailmovie.domain.entity.DetailContentModel
import id.rezkyauliapratama.fdetailmovie.domain.repository.DetailContentRepository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

@FeatureScope
class DetailContentRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager
) : DetailContentRepository {

    override fun setFavorite(itemId: String, favoriteType: FavoriteType): Single<Boolean> {
        val favoriteTable = FavoriteTable(
            itemType = favoriteType
        )
        return dataManager.getFavorite(itemId).onErrorResumeNext{
            Timber.e("setFavorite Error : $it ")
            if (it is EmptyResultSetException){
                Single.just(favoriteTable)
            }else{
                Single.error(it)
            }

        }.map {
            Timber.e("favorite table map : $it")
            it.itemId.isEmpty()
        }.flatMap {
            Timber.e("boolean : $it")

            if (it){
                dataManager.setFavorite(favoriteTable.apply {
                    this.itemId = itemId
                }).map {
                    it > 0
                }
            } else {
                dataManager.deleteFavorite(itemId).toSingle {
                    false
                }
            }
        }
    }

    override fun getFavorite(itemId: String): Single<FavoriteTable> {
        return dataManager.getFavorite(itemId)
    }

    override fun getTvShowMovie(tvShowId: Int): Single<DetailContentModel> {
        return dataManager.getDetailTvShow(tvShowId, LocaleManager.getLanguagePref(context)).map {
            it.mapToDomain()
        }
    }

    override fun getDetailMovie(movieId: Int): Single<DetailContentModel> {
        return dataManager.getDetailMovie(movieId, LocaleManager.getLanguagePref(context)).map {
            it.mapToDomain()
        }
    }

}
