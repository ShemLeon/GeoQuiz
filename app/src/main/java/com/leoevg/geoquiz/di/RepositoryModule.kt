package com.leoevg.geoquiz.di

import com.leoevg.geoquiz.data.repository.AuthRepositoryImpl
import com.leoevg.geoquiz.data.repository.QuizRepositoryImpl
import com.leoevg.geoquiz.domain.repository.AuthRepository
import com.leoevg.geoquiz.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
/* синглтон - зависимость создасться  1 раз и будет неизменяема на протяжении всей работы приложения
 если его запросить, например, в нескольких ViewModel - он будет везде одинаков
 */

object RepositoryModule { // ф-я, которая знает как билдить модули
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
}
















