package id.innovation.libdatabase.entity

import android.database.Cursor
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import id.innovation.libdatabase.entity.FavoriteTable.Companion.Column.ITEM_ID
import id.innovation.libdatabase.entity.FavoriteTable.Companion.Column.ITEM_TYPE
import id.innovation.libdatabase.entity.FavoriteTable.Companion.TABLE_NAME
import java.io.Serializable


@Entity(tableName = TABLE_NAME)
data class FavoriteTable(
    @PrimaryKey
    @ColumnInfo(name = Column.ITEM_ID)
    var itemId: String = "",

    @ColumnInfo(name = Column.ITEM_TYPE)
    val itemType: FavoriteType
) {

    companion object {
        const val TABLE_NAME = "favorite"

        object Column {
            const val ITEM_ID = "item_id"
            const val ITEM_TYPE = "item_type"
        }
    }
}

class StatusConverter : Serializable {

    @TypeConverter
    fun fromEnum(favoriteType: FavoriteType): Int {
        return favoriteType.code
    }

    @TypeConverter
    fun toEnum(code: Int): FavoriteType {
        return when (code) {
            0 -> FavoriteType.MOVIE
            else -> FavoriteType.TV_SHOW
        }
    }
}

enum class FavoriteType constructor(val code: Int) {
    MOVIE(0),
    TV_SHOW(1)
}

fun Cursor.assign(): FavoriteTable {
    return FavoriteTable(
        getString(getColumnIndex(ITEM_ID)),
        if (getInt(getColumnIndex(ITEM_TYPE)) == 0) FavoriteType.MOVIE else FavoriteType.TV_SHOW
    )
}