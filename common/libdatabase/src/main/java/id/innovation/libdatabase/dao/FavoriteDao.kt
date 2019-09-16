package id.innovation.libdatabase.dao

import androidx.room.Dao
import androidx.room.Query
import id.innovation.libdatabase.common.BaseDao
import id.innovation.libdatabase.entity.FavoriteTable
import id.innovation.libdatabase.entity.FavoriteTable.Companion.Column.ITEM_ID
import id.innovation.libdatabase.entity.FavoriteTable.Companion.Column.ITEM_TYPE
import id.innovation.libdatabase.entity.FavoriteTable.Companion.TABLE_NAME
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao : BaseDao<FavoriteTable> {

    @Query("SELECT * FROM $TABLE_NAME WHERE $ITEM_ID = :itemId")
    fun getItemById(itemId: String): Single<FavoriteTable>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ITEM_TYPE = :itemType")
    fun getItemByType(itemType: Int): Single<List<FavoriteTable>>

    @Query("SELECT * FROM $TABLE_NAME")
    override fun getItems(): Single<List<FavoriteTable>>

    @Query("DELETE FROM $TABLE_NAME")
    override fun deleteAll()

    @Query("DELETE FROM $TABLE_NAME where $ITEM_ID = :itemId")
    fun deleteById(itemId: String): Completable

}

