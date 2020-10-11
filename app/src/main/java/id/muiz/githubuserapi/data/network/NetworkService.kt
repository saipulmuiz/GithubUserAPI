package id.muiz.githubuserapi.data.network

import id.muiz.githubuserapi.data.entity.SearchUserResponse
import id.muiz.githubuserapi.data.entity.UserDetailResponse
import id.muiz.githubuserapi.data.entity.UserFollowersResponse
import id.muiz.githubuserapi.data.entity.UserFollowingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    /**
     * SM Endpoints search User
     */
    @GET("search/users?")
    suspend fun getSearchUser(
        @Query("q") q : String
    ) : Response<SearchUserResponse>

    /**
     * SM Endpoints Detail User
     */
    @GET("users/{username}")
    @Headers("Authorization: token 5a6f5d27996ffa0dad3d159e89f0fc564c3060de")
    suspend fun getDetailUser(
        @Path("username") username: String
    ) : Response<UserDetailResponse>

    /**
     * SM Endpoints Followers
     */
    @GET("users/{username}/followers")
    suspend fun getFollowerUser(
        @Path("username") username: String
    ) : Response<UserFollowersResponse>

    /**
     * SM Endpoints Following
     */
    @GET("users/{username}/following")
    suspend fun getFollowingUser(
        @Path("username") username: String
    ) : Response<UserFollowingResponse>


}