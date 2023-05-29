package com.example.composearchitectureexample.ui.main.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composearchitectureexample.data.local.entity.UserEntity
import com.example.composearchitectureexample.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _nameFieldState = mutableStateOf("")
    val nameFiledState : State<String>
    get() = _nameFieldState
    fun setNameFieldState(name : String) {
        _nameFieldState.value =  name
    }

    private val _ageFieldState = mutableStateOf("")
    val ageFieldState : State<String>
        get() = _ageFieldState
    fun setAgeFieldState(name : String) {
        _ageFieldState.value =  name
    }

    /**
     * 유저를 데이터베이스에 insert하는 함수
     * **/
    fun insertUser() {
        val user = UserEntity(null , nameFiledState.value , ageFieldState.value.toInt())
        viewModelScope.launch {
            mainRepository.insertUser(user)
        }
    }
}