package com.leoevg.geoquiz.data.domain.repository

import com.leoevg.geoquiz.data.model.Quiz

// в DOMAIN мы объявляем самые абстрактные вещи, которые не имеют реализации и
// являются ядром, для следующего наследования / использования
// здесь я создам ф-ции отвечающие за загрузку данных


interface QuizRepository {
    suspend fun loadQuiz(quizTypeGame: String): Quiz

}

