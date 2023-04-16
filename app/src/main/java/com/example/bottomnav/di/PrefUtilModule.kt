package com.example.bottomnav.di

import android.content.Context
import com.example.bottomnav.util.PrefUtil
import com.example.bottomnav.util.PrefUtilInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefUtilModule {

    @Binds
    @Singleton
    abstract fun bindPrefUtil(prefUtil: PrefUtil): PrefUtilInterface
}