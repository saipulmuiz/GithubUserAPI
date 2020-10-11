package id.muiz.githubuserapi

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import id.muiz.githubuserapi.di.component.DaggerAppComponent

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

}