package id.innovation.libdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.libdatabase.BuildConfig
import id.innovation.libdatabase.dao.FavoriteDao
import id.innovation.libdatabase.entity.FavoriteTable
import id.innovation.libdatabase.entity.StatusConverter

@Database(
    entities = [FavoriteTable::class],
    version = BuildConfig.VERSION_CODE
)
@TypeConverters(StatusConverter::class)
abstract class RoomDb : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
