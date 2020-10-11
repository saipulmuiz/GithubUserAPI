package id.muiz.githubuserapi.di.module

import dagger.Module
import dagger.Provides
import id.muiz.githubuserapi.data.network.NetworkService
import id.muiz.githubuserapi.di.DataScope
import id.muiz.githubuserapi.network.Network

@Module
class DataModule {

    @Provides
    @DataScope
    fun provideNetworkService() : NetworkService {
        return Network.retrofitClient().create(NetworkService::class.java)
    }

}