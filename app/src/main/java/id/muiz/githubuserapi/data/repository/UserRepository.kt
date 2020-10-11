package id.muiz.githubuserapi.data.repository

import id.muiz.githubuserapi.data.db.entity.UserFavorite
import id.muiz.githubuserapi.data.entity.SearchUserResponse
import id.muiz.githubuserapi.data.entity.UserDetailResponse
import id.muiz.githubuserapi.data.entity.UserFollowersResponse
import id.muiz.githubuserapi.data.entity.UserFollowingResponse
import retrofit2.Response

interface UserRepository {

    /**
     * Remote
     */
    suspend fun getUserApi(username: String) : Response<SearchUserResponse>

    suspend fun getUserDetailApi(username: String) : Response<UserDetailResponse>

    suspend fun getFollowers(username: String) : Response<UserFollowersResponse>

    suspend fun getFollowing(username: String) : Response<UserFollowingResponse>

    /**
     * Local
     */
    suspend fun fetchAllFavorit() : List<UserFavorite>

    suspend fun getFavByUsername(username: String) : List<UserFavorite>

    suspend fun addUserToFavDB(userFavorite: UserFavorite)

    suspend fun deleteUserDb(userFavorite: UserFavorite)



}