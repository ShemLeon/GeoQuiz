package com.leoevg.geoquiz.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.domain.repository.QuizRepository
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.data.model.Quiz
import com.leoevg.geoquiz.data.util.getDataOnce

// здесь будем реализовывать загрузку данных из FireBase
class QuizRepositoryImpl: QuizRepository {
    override suspend fun loadQuiz(quizTypeGame: String): Quiz {
        val snapshot = FirebaseDatabase.getInstance().reference.child("Questions").child("Type_game").child(quizTypeGame).getDataOnce()

        val questions = snapshot.children.map { questionSnapshot ->
            val hint = questionSnapshot.child("hint").value.toString()
            val rightAnswer = questionSnapshot.child("right").value.toString()

            val picturesUrls = questionSnapshot.child("pictures").children.map { pictureSnapshot ->
                return@map pictureSnapshot.value.toString()
            }

            val answerOptions = questionSnapshot.child("answers").children.map { answerSnapshot ->
                return@map AnswerOption(
                    id = answerSnapshot.key?.toInt() ?: -1,
                    optAnswer = answerSnapshot.value.toString()
                )
            }

            return@map Question(
                id = questionSnapshot.key?.toInt() ?: -1,
                rightAnswer = rightAnswer,
                hint = hint,
                answerOptions = answerOptions,
                picturesUrls = picturesUrls
            )
        }

        return Quiz(
            typeGame = quizTypeGame,
            questions = questions
        )
    }
}