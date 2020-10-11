package id.muiz.githubuserapi.di.module

import dagger.Module
import dagger.Provides
import id.muiz.githubuserapi.data.db.dao.UserFavoriteDao
import id.muiz.githubuserapi.data.network.NetworkService
import id.muiz.githubuserapi.data.repository.UserRepository
import id.muiz.githubuserapi.data.repository.UserRepositoryImpl
import id.muiz.githubuserapi.di.DataScope

@Module(includes = [DataModule::class, RoomModule::class])
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        @DataScope service: NetworkService,
        userFavoriteDao: UserFavoriteDao
    ) : UserRepository {
        return UserRepositoryImpl(service, userFavoriteDao)
    }
}