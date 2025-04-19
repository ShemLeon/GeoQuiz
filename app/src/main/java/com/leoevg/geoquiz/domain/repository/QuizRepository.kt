package com.leoevg.geoquiz.domain.repository

import com.leoevg.geoquiz.data.model.Quiz

interface QuizRepository {
    suspend fun loadQuiz(quizTypeGame: String): Quiz
}