package com.leoevg.geoquiz.di

import com.leoevg.geoquiz.data.repository.AuthRepositoryImpl
import com.leoevg.geoquiz.data.repository.QuizRepositoryImpl
import com.leoevg.geoquiz.data.repository.UserRepositoryImpl
import com.leoevg.geoquiz.domain.repository.AuthRepository
import com.leoevg.geoquiz.domain.repository.QuizRepository
import com.leoevg.geoquiz.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
// function, who knows how to build modules
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(): QuizRepository {
        return QuizRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}
















