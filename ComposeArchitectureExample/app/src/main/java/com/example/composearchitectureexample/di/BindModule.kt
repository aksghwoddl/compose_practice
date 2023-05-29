package com.example.composearchitectureexample.di

import com.example.composearchitectureexample.data.repository.MainRepositoryImpl
import com.example.composearchitectureexample.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    @Singleton
    abstract fun bindRepository(mainRepositoryImpl: MainRepositoryImpl) : MainRepository
}