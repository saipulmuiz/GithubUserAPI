package id.muiz.githubuserapi.domain

import id.muiz.githubuserapi.core.state.ResultState
import id.muiz.githubuserapi.core.util.safeApiCall
import id.muiz.githubuserapi.data.db.entity.UserFavorite
import id.muiz.githubuserapi.data.entity.SearchUserResponse
import id.muiz.githubuserapi.data.entity.UserDetailResponse
import id.muiz.githubuserapi.data.entity.UserFollowersResponse
import id.muiz.githubuserapi.data.entity.UserFollowingResponse
import id.muiz.githubuserapi.data.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    /**
     * Remote
     */
    suspend fun getUserApi(username: String) : ResultState<SearchUserResponse> {
        return safeApiCall {
            val response = userRepository.getUserApi(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getUserDetailApi(username: String) : ResultState<UserDetailResponse> {
        return safeApiCall {
            val response = userRepository.getUserDetailApi(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getFollowers(username: String) : ResultState<UserFollowersResponse> {
        return safeApiCall {
            val response = userRepository.getFollowers(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getFollowing(username: String) : ResultState<UserFollowingResponse> {
        return safeApiCall {
            val response = userRepository.getFollowing(username)
            try {
                ResultState.Success(response.body())
            }catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    /**
     * Local
     */
    suspend fun fetchAllFavorit() : ResultState<List<UserFavorite>> {
        return try {
            val result = userRepository.fetchAllFavorit()
            ResultState.Success(result)
        }catch (e: Exception) {
            ResultState.Error(e.localizedMessage, 500)
        }
    }

    suspend fun deleteUserDb(userFavorite: UserFavorite) {
        try {
            userRepository.deleteUserDb(userFavorite)
        }catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun addUserToFavDB(userFavorite: UserFavorite) {
        try {
            userRepository.addUserToFavDB(userFavorite)
        }catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getFavByUsername(username: String) : ResultState<List<UserFavorite>> {
        return try {
            val result = userRepository.getFavByUsername(username)
            ResultState.Success(result)
        }catch (e: Exception) {
            ResultState.Error(e.localizedMessage, 500)
        }
    }
}