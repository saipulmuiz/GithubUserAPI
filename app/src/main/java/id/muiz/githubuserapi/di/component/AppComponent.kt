package id.muiz.githubuserapi.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.muiz.githubuserapi.BaseApplication
import id.muiz.githubuserapi.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        RoomModule::class,
        FragmentBuilderModule::class,
        ContentProviderBuilderModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}