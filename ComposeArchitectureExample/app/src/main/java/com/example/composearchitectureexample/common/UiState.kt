package com.example.composearchitectureexample.common

sealed class UiState<out T> {
    data class Loading<T>(val isLoading : Boolean) : UiState<T>()
    data class Failure<T>(val errorMessage : String) : UiState<T>()
    data class Success<T>(val data : T) : UiState<T>()
}