package com.example.composearchitectureexample.di

import android.content.Context
import androidx.room.Room
import com.example.composearchitectureexample.common.DatabaseConst
import com.example.composearchitectureexample.data.local.dao.UserDao
import com.example.composearchitectureexample.data.local.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : UserDatabase {
        return Room.databaseBuilder(
            context ,
            UserDatabase::class.java ,
            DatabaseConst.USER_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: UserDatabase) : UserDao {
        return database.userDao()
    }
}