package com.example.composearchitectureexample.domain.repository

import com.example.composearchitectureexample.common.UiState
import com.example.composearchitectureexample.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun insertUser(userEntity: UserEntity)

    suspend fun getAllUser() : Flow<UiState<MutableList<UserEntity>>>

}