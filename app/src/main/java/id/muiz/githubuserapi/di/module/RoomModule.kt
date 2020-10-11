package id.muiz.githubuserapi.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import id.muiz.githubuserapi.data.db.AppDatabase
import id.muiz.githubuserapi.data.db.dao.UserFavoriteDao
import javax.inject.Singleton

@Module
class RoomModule {

    private val mDatabaseName = "favorit_DB"

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            mDatabaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserFavoriteDao(appDatabase: AppDatabase) : UserFavoriteDao {
        return appDatabase.userFavDao()
    }

}