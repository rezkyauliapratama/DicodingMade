package id.innovation.libcore.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import id.innovation.libcore.di.annotation.ApplicationContext
import id.innovation.libdatabase.RoomDb
import id.innovation.libdatabase.dao.FavoriteDao
import javax.inject.Singleton

@Module
class DatabaseModule(private val databaseName: String) {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): RoomDb {
        return Room.databaseBuilder(context, RoomDb::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Provides
    fun provideFavoriteDao(database: RoomDb): FavoriteDao {
        return database.favoriteDao()
    }

}
