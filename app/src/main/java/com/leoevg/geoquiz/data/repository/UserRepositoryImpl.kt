package com.leoevg.geoquiz.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.util.CHILD_CURRENT_MAX_SCORE
import com.leoevg.geoquiz.data.util.NODE_USERS
import com.leoevg.geoquiz.data.util.getDataOnce
import com.leoevg.geoquiz.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    // Получение максимума
    override suspend fun getMaxResultByUserId(
        email: String
    ): Double? {
        return try {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return null
            val snapshot = FirebaseDatabase.getInstance().reference
             .child(NODE_USERS)
             .child(uid)
             .child(CHILD_CURRENT_MAX_SCORE)
             .getDataOnce()

            snapshot.getValue(Double::class.java)
        } catch (e: Exception) {
            null}
    }

// Обновление максимума
    override suspend fun updateMaxResultByUserId(email: String, newMaxScore: Double): Boolean {
        return try {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return false
            FirebaseDatabase.getInstance().reference
                .child(NODE_USERS)
                .child(uid)
                .child(CHILD_CURRENT_MAX_SCORE)
                .setValue(newMaxScore)
            true
        } catch (e: Exception) {
            false
        }
    }

}



/*
    1. Добавить в domain - интерфейс UserRepository,
    2. Он будет содержать 1 метод suspend getMaxResultByUserId -
    этот метод должен возвращать какой-то дабл - максимальный результат пользователя по Id
    3. Добавить class внутри data--repository - UserRepositoryImpl, в нем заимплементить мой интерфейс,
    аналог в authRepositoryImpl authRepositoryImpl
    4. Реализовать наш метод, обратится к БД и вытащить из пользователя (аналог метода getDanaOnce)
    5. запихнуть результат в FinishScreen в max  - из БД currentMaxScore
    Text(
            stringResource(R.string.max_score + $maxScore),
            modifier = Modifier,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
 */