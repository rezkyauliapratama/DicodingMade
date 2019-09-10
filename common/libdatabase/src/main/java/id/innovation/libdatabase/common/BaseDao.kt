package id.innovation.libdatabase.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Single

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(region: T): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>): Single<List<Long>>

    @Update
    fun update(items: T)

    @Update
    fun update(items: List<T>)

    @Delete
    fun delete(item: T): Single<Unit>

    @Delete
    fun delete(items: List<T>)

    fun deleteAll()

    fun getItems(): Single<List<T>>
}
