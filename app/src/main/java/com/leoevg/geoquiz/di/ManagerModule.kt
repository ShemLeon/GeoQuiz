package com.leoevg.geoquiz.di

import android.content.Context
import androidx.annotation.UiContext
import com.leoevg.geoquiz.data.manager.SharedPrefManager
import com.leoevg.geoquiz.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {
    @Provides
    @Singleton
    fun provideSharedPrefsManager(@ApplicationContext context: Context): SharedPrefManager{
        return SharedPrefManager(context)
    }
}















