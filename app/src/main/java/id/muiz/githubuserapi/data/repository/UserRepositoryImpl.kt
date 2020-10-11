package id.muiz.githubuserapi.data.repository

import id.muiz.githubuserapi.data.db.dao.UserFavoriteDao
import id.muiz.githubuserapi.data.db.entity.UserFavorite
import id.muiz.githubuserapi.data.entity.SearchUserResponse
import id.muiz.githubuserapi.data.entity.UserDetailResponse
import id.muiz.githubuserapi.data.entity.UserFollowersResponse
import id.muiz.githubuserapi.data.entity.UserFollowingResponse
import id.muiz.githubuserapi.data.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val userFavoriteDao: UserFavoriteDao
) : UserRepository {

    /**
     * API
     */
    override suspend fun getUserApi(username: String): Response<SearchUserResponse> {
        return networkService.getSearchUser(username)
    }

    override suspend fun getUserDetailApi(username: String): Response<UserDetailResponse> {
        return networkService.getDetailUser(username)
    }

    override suspend fun getFollowers(username: String): Response<UserFollowersResponse> {
        return networkService.getFollowerUser(username)
    }

    override suspend fun getFollowing(username: String): Response<UserFollowingResponse> {
        return networkService.getFollowingUser(username)
    }

    /**
     * LOKAL
     */
    override suspend fun fetchAllFavorit(): List<UserFavorite> {
        return userFavoriteDao.fetchAllUsers()
    }

    override suspend fun getFavByUsername(username: String): List<UserFavorite> {
        return userFavoriteDao.getFavByUsername(username)
    }

    override suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        return userFavoriteDao.addUserToFavDB(userFavorite)
    }

    override suspend fun deleteUserDb(userFavorite: UserFavorite) {
        return userFavoriteDao.deleteUserDb(userFavorite)
    }

}