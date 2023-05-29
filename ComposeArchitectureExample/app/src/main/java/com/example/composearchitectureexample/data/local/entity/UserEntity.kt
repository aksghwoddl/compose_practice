package com.example.composearchitectureexample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composearchitectureexample.common.DatabaseConst

@Entity(tableName = DatabaseConst.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val index : Int? ,
    val name : String ,
    val age : Int
)