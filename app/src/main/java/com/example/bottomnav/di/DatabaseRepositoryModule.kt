package com.example.bottomnav.di

import com.example.bottomnav.data.repository.DatabaseRepository
import com.example.bottomnav.data.repository.DatabaseRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseRepositoryModule {

    @Binds
    abstract fun bindDatabaseRepository(databaseRepository: DatabaseRepository): DatabaseRepositoryInterface

}