package id.muiz.githubuserapi.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.muiz.githubuserapi.feature.follower.FollowersFragment
import id.muiz.githubuserapi.feature.following.FollowingFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeFollowingFragment() : FollowingFragment

    @ContributesAndroidInjector
    abstract fun contributeFollowerFragment() : FollowersFragment
}