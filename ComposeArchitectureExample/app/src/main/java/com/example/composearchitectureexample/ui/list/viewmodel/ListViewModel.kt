package com.example.composearchitectureexample.ui.list.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composearchitectureexample.common.UiState
import com.example.composearchitectureexample.data.local.entity.UserEntity
import com.example.composearchitectureexample.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ListViewModel"
@HiltViewModel
class ListViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _userUiState = MutableStateFlow<UiState<MutableList<UserEntity>>>(UiState.Loading(isLoading = false))
    val userUiState : StateFlow<UiState<MutableList<UserEntity>>>
    get() = _userUiState

    /**
     * 유저 목록을 불러오는 함수
     * **/
    fun getAllUser() {
        viewModelScope.launch {
            _userUiState.value = UiState.Loading(isLoading = true)
            mainRepository.getAllUser().collect{
                Log.d(TAG, "getAllUser : ${it}")
                _userUiState.value = it
            }
        }
    }
}