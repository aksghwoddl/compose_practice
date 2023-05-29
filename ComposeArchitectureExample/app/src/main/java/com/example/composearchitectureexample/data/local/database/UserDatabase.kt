package com.example.composearchitectureexample.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composearchitectureexample.data.local.dao.UserDao
import com.example.composearchitectureexample.data.local.entity.UserEntity

@Database(entities = [UserEntity::class] , version = 1 , exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}