package id.muiz.githubuserapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import id.muiz.githubuserapi.data.db.dao.UserFavoriteDao
import id.muiz.githubuserapi.data.db.entity.UserFavorite

@Database(
    entities = [UserFavorite::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userFavDao(): UserFavoriteDao

}