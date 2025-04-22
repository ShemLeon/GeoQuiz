package com.leoevg.geoquiz.data.repository

import com.leoevg.geoquiz.data.domain.repository.QuizRepository
import com.leoevg.geoquiz.data.model.Quiz
// здесь будем реализовывать загрузку данных из FireBase
class QuizRepositoryImpl: QuizRepository {
    override suspend fun loadQuiz(quizTypeGame: String): Quiz {
        TODO("Not yet implemented")
    }
}