package com.example.composearchitectureexample.data.repository

import com.example.composearchitectureexample.common.UiState
import com.example.composearchitectureexample.data.local.dao.UserDao
import com.example.composearchitectureexample.data.local.entity.UserEntity
import com.example.composearchitectureexample.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : MainRepository {

    override suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    override suspend fun getAllUser(): Flow<UiState<MutableList<UserEntity>>> {
        return flow<UiState<MutableList<UserEntity>>>{
            emit(UiState.Success(userDao.showAllUser()))

        }.catch { // 에러 발생시에는 UiState를 Failure와 함께 반환
            emit(UiState.Failure("데어터 로딩중 문제 발생!!"))
        }
    }
}