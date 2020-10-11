package id.muiz.githubuserapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.muiz.githubuserapi.feature.detail.DetailActivity
import id.muiz.githubuserapi.feature.favorit.FavoritActivity
import id.muiz.githubuserapi.feature.main.MainActivity
import id.muiz.githubuserapi.feature.settings.PengaturanActivity
import id.muiz.githubuserapi.feature.splash.SplashActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeUserDetailActivity() : DetailActivity

    @ContributesAndroidInjector
    abstract fun contributeFavoriteUserActivity() : FavoritActivity

    @ContributesAndroidInjector
    abstract fun contributeSettingsActivity() : PengaturanActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity() : SplashActivity

}