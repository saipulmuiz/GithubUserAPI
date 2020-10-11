package id.muiz.githubuserapi.feature.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.muiz.githubuserapi.core.state.LoaderState
import id.muiz.githubuserapi.core.state.ResultState
import id.muiz.githubuserapi.data.entity.UserFollowingResponseItem
import id.muiz.githubuserapi.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FollowingViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    private val _resultUserFollowing = MutableLiveData<List<UserFollowingResponseItem>>()
    val resultUserFollowing : LiveData<List<UserFollowingResponseItem>>
        get() = _resultUserFollowing

    fun getUserFollowing(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            val result = userUseCase.getFollowing(username)
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserFollowing.postValue(result.data)
                }
            }
        }
    }

}