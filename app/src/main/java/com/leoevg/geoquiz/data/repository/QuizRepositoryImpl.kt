package com.leoevg.geoquiz.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.domain.repository.QuizRepository
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.data.model.Quiz
import com.leoevg.geoquiz.data.util.CHILD_ANSWERS
import com.leoevg.geoquiz.data.util.CHILD_HINT
import com.leoevg.geoquiz.data.util.CHILD_PICTURES
import com.leoevg.geoquiz.data.util.CHILD_QUESTIONS
import com.leoevg.geoquiz.data.util.CHILD_RIGHT
import com.leoevg.geoquiz.data.util.CHILD_TYPE_GAME
import com.leoevg.geoquiz.data.util.getDataOnce

// здесь будем реализовывать загрузку данных из FireBase
class QuizRepositoryImpl: QuizRepository {
    override suspend fun loadQuiz(quizTypeGame: String): Quiz {
        val snapshot = FirebaseDatabase.getInstance().reference.
            child(CHILD_QUESTIONS).child(CHILD_TYPE_GAME).child(quizTypeGame).getDataOnce()

        val questions = snapshot.children.map { questionSnapshot ->
            val hint = questionSnapshot.child(CHILD_HINT).value.toString()
            val rightAnswer = questionSnapshot.child(CHILD_RIGHT).value.toString()

            // @map - фича котлина, упростит код и пройдется по всем элементам и получит что-то из них
            // оно соберет целый новый лист новых значений
            val picturesUrls = questionSnapshot.child(CHILD_PICTURES).children.map { pictureSnapshot ->
                return@map pictureSnapshot.value.toString()
            }

            val answerOptions = questionSnapshot.child(CHILD_ANSWERS).children.map { answerSnapshot ->
                return@map AnswerOption(
                    // по умолчанию если он null - айди ответа  -1
                    id = answerSnapshot.key?.toInt() ?: -1,
                    optAnswer = answerSnapshot.value.toString()
                )
            }

            return@map Question(
                id = questionSnapshot.key ?: "",
                rightAnswer = rightAnswer,
                hint = hint,
                answerOptions = answerOptions,
                picturesUrls = picturesUrls
            )
        }

        return Quiz(
            typeGame = quizTypeGame,
            questions = questions.shuffled()
        )
    }
}