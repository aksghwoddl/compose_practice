package com.example.composearchitectureexample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composearchitectureexample.common.DatabaseConst
import com.example.composearchitectureexample.data.local.entity.UserEntity

@Dao
interface UserDao {

    /**
     * User 입력하는 함수
     * @param userEntity : 입력할 유저 정보
     * **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    /**
     * 모든 유저 정보를 불러오는 함수
     * **/
    @Query("SELECT * FROM ${DatabaseConst.USER_TABLE}")
    suspend fun showAllUser() : MutableList<UserEntity>

}