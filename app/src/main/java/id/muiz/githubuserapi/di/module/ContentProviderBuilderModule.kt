package id.muiz.githubuserapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.muiz.githubuserapi.feature.contentprovider.MyContentProvider

@Module(includes = [RoomModule::class])
abstract class ContentProviderBuilderModule  {

    @ContributesAndroidInjector
    abstract fun contributeContentProvider() : MyContentProvider
}