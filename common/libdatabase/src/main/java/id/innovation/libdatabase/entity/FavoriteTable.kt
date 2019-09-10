package id.innovation.libdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import id.innovation.libdatabase.entity.FavoriteTable.Companion.TABLE_NAME
import java.io.Serializable


@Entity(tableName = TABLE_NAME)
data class FavoriteTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Column.ID)
    val id: Long,

    @ColumnInfo(name = Column.ITEM_TYPE)
    val itemType: FavoriteType,

    @ColumnInfo(name = Column.ITEM_ID)
    val itemId: String
) {

    companion object {
        const val TABLE_NAME = "favorite"

        object Column {
            const val ID = "id"
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