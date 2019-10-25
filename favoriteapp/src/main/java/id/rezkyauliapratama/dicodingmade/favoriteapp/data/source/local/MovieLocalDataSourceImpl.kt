package id.rezkyauliapratama.dicodingmade.favoriteapp.data.source.local

import android.content.Context
import android.database.Cursor
import android.net.Uri
import id.innovation.libcore.contentprovider.DataPath
import io.reactivex.Single
import timber.log.Timber

class MovieLocalDataSourceImpl(private val context: Context) : MovieLocalDataSource {

    override fun getFavorite(type: Int): Single<List<String>> {
        return Single.create {
            var uri: Uri = DataPath.CONTENT_URI
            uri = uri.buildUpon().appendPath(type.toString()).build()

            Timber.e("uri : $uri")
            val cursor: Cursor? = context.contentResolver?.query(
                uri,
                null,
                null,
                null,
                null
            )

            if (cursor == null) {
                Timber.e("cursor == null")
                it.onError(Exception("No Data"))
            }

            val itemIds = mutableListOf<String>()
            if (cursor!!.moveToFirst()) do {
                val str = cursor.getString(cursor.getColumnIndex("item_id"))
                itemIds.add(str)
                Timber.e("str : $str")
            } while (cursor.moveToNext())
            cursor.close()

            it.onSuccess(itemIds)
        }
    }
}