package id.muiz.githubuserapi.feature.follower

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.muiz.githubuserapi.core.state.LoaderState
import id.muiz.githubuserapi.core.state.ResultState
import id.muiz.githubuserapi.data.entity.UserFollowersResponseItem
import id.muiz.githubuserapi.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowerViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * Loader state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    /**
     * Error state
     */
    private val _error = MutableLiveData<String>()

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()

    /**
     * State Followers
     */
    private val _resultUserFollower = MutableLiveData<List<UserFollowersResponseItem>>()
    val resultUserFollower : LiveData<List<UserFollowersResponseItem>>
        get() = _resultUserFollower

    fun getUserFollowers(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            val result = userUseCase.getFollowers(username)
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserFollower.postValue(result.data)
                }
                is ResultState.Error -> {
                    _error.postValue(result.error)
                }
                is ResultState.NetworkError -> {
                    _networkError.postValue(true)
                }
            }
        }
    }


}