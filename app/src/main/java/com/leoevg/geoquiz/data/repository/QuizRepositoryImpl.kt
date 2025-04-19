package com.leoevg.geoquiz.data.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.leoevg.geoquiz.data.model.Quiz
import com.leoevg.geoquiz.data.util.getDataOnce
import com.leoevg.geoquiz.domain.repository.QuizRepository

class QuizRepositoryImpl: QuizRepository {
    override suspend fun loadQuiz(quizTypeGame: String): Quiz {
        val snapshot = FirebaseDatabase.getInstance().reference.child("Questions").child("Type_game").child(quizTypeGame).getDataOnce()

        snapshot.children.forEach { questionSnapshot ->
            val hint = questionSnapshot.child("hint").value.toString()
            val right = questionSnapshot.child("right").value.toString()
        }
    }
}