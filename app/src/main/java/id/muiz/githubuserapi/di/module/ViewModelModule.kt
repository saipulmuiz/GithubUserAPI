package id.muiz.githubuserapi.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.muiz.githubuserapi.feature.detail.DetailViewModel
import id.muiz.githubuserapi.feature.favorit.FavoritUserViewModel
import id.muiz.githubuserapi.feature.follower.FollowerViewModel
import id.muiz.githubuserapi.feature.following.FollowingViewModel
import id.muiz.githubuserapi.feature.main.MainViewModel
import id.muiz.githubuserapi.viewmodel.ViewModelFactory
import id.muiz.githubuserapi.viewmodel.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindUserDetailViewModel(viewModel: DetailViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowerViewModel::class)
    internal abstract fun bindFollowerViewModel(viewModel: FollowerViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FollowingViewModel::class)
    internal abstract fun bindFollowingViewModel(viewModel: FollowingViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritUserViewModel::class)
    internal abstract fun bindFavoriteUserViewModel(viewModel: FavoritUserViewModel) : ViewModel


}