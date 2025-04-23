package com.leoevg.geoquiz.data.repository
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.domain.repository.QuizRepository
import com.leoevg.geoquiz.data.model.Quiz
import com.leoevg.geoquiz.data.util.getDataOnce

// здесь будем реализовывать загрузку данных из FireBase
class QuizRepositoryImpl: QuizRepository {
    override suspend fun loadQuiz(quizTypeGame: String): Quiz {
        val snapshot = FirebaseDatabase.getInstance().reference.
                        child("Questions").child("Type_game").child(quizTypeGame).
                        getDataOnce()
        snapshot.children.forEach { questionSnapshot ->
            val hint = questionSnapshot.child("hint").value.toString()
            val right = questionSnapshot.child("right").value.toString()
        }

    }
}