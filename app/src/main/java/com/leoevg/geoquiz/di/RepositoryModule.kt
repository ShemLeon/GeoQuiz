package com.leoevg.geoquiz.di

import com.leoevg.geoquiz.data.repository.LoginRepositoryImpl
import com.leoevg.geoquiz.data.repository.QuizRepositoryImpl
import com.leoevg.geoquiz.domain.repository.LoginRepository
import com.leoevg.geoquiz.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
/* анотация о том, что должен быть установлен синглтон
 синглтон значит, что зависимость создасться  1 раз и будет неизменяема на протяжении всей работы приложения
 если его запросить, например, в 3х ViewModel - он будет везде одинаков
 */

object RepositoryModule { // ф-я, которая знает как билдить модули
    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(): RegisterRepository {
        return RegisterRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(): QuizRepository {
        return QuizRepositoryImpl()
    }
}
















