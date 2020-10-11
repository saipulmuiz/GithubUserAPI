package id.muiz.githubuserapi.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.muiz.githubuserapi.core.state.LoaderState
import id.muiz.githubuserapi.core.state.ResultState
import id.muiz.githubuserapi.data.entity.UserSearchResponseItem
import id.muiz.githubuserapi.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {


    /**
     * Loading state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    /**
     * Error
     */
    private val _error  = MutableLiveData<String>()

    /**
     * Network Error
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
        get() = _networkError

    /**
     * Result user from API
     */
    private val _resultUserApi = MutableLiveData<List<UserSearchResponseItem>>()
    val resultUserApi: LiveData<List<UserSearchResponseItem>>
        get() = _resultUserApi

    fun getUserFromApi(query: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            val result = userUseCase.getUserApi(query)
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserApi.postValue(result.data?.userItems)
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