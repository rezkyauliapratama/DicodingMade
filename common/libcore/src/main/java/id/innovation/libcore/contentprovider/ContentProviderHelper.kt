package id.innovation.libcore.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import androidx.room.RoomDatabase
import id.innovation.libdatabase.RoomDb
import id.innovation.libdatabase.dao.FavoriteDao
import timber.log.Timber

class ContentProviderHelper : ContentProvider() {

    companion object {
        const val CODE_FAVORITE = 400
        const val CODE_FAVORITE_WITH_ID = 401
    }


    lateinit var favoriteDao: FavoriteDao

    private val sUriMatcher: UriMatcher = buildUriMatcher()

    fun buildUriMatcher(): UriMatcher {
        val matcher =
            UriMatcher(UriMatcher.NO_MATCH)
        val authority: String? =
            DataPath.CONTENT_AUTHORITY

        matcher.addURI(
            authority,
            DataPath.PATH_FAVORITE,
            CODE_FAVORITE
        )
        matcher.addURI(
            authority,
            DataPath.PATH_FAVORITE + "/#",
            CODE_FAVORITE_WITH_ID
        )
        return matcher
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        val match: Int =
            sUriMatcher.match(uri)
        var retCursor: Cursor? = null

        Timber.e("uri query : $uri")
        Timber.e("uri match : $match")
        when (match) {
            CODE_FAVORITE_WITH_ID -> {
                val id: String = uri.pathSegments[1]
                Timber.e("favorite type $id")

                retCursor = favoriteDao.getCursorAll(id.toInt())
            }
        }

        if (retCursor == null) {
            Timber.e("retcursor null !!!!!")
            return null
        }

        retCursor.setNotificationUri(context.contentResolver, uri)
        return retCursor
    }

    override fun onCreate(): Boolean {
        val database = Room.databaseBuilder(context!!, RoomDb::class.java, "database")
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()

        favoriteDao = database.favoriteDao()
        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

}