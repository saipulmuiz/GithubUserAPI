package id.muiz.githubuserapi.feature.favorit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.muiz.githubuserapi.core.state.ResultState
import id.muiz.githubuserapi.data.db.entity.UserFavorite
import id.muiz.githubuserapi.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritUserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * error
     */
    private val _error = MutableLiveData<String>()

    /**
     * Result from db
     */
    private val _resultUserFromDb = MutableLiveData<List<UserFavorite>>()
    val resultUserFromDb : LiveData<List<UserFavorite>>
        get() = _resultUserFromDb

    init {
        fetchAllUserFavorite()
    }

    fun fetchAllUserFavorite() {
        viewModelScope.launch {
            when (val result = userUseCase.fetchAllFavorit()) {
                is ResultState.Success -> _resultUserFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

}