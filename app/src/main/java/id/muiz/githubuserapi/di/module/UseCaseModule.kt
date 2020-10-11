package id.muiz.githubuserapi.di.module

import dagger.Module
import dagger.Provides
import id.muiz.githubuserapi.data.repository.UserRepository
import id.muiz.githubuserapi.domain.UserUseCase

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    fun provideUserUseCaseModule(userRepository: UserRepository) : UserUseCase {
        return UserUseCase(userRepository)
    }
}