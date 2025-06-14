package com.leoevg.geoquiz.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.util.CHILD_CURRENT_MAX_SCORE
import com.leoevg.geoquiz.data.util.CHILD_IS_ADMIN
import com.leoevg.geoquiz.data.util.NODE_USERS
import com.leoevg.geoquiz.data.util.getDataOnce
import com.leoevg.geoquiz.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    // Получение максимума
    override suspend fun getMaxResultByUserId(
        uId: String
    ): Int? {
        return try {
           val snapshot = FirebaseDatabase.getInstance().reference
             .child(NODE_USERS)
             .child(uId)
             .child(CHILD_CURRENT_MAX_SCORE)
             .getDataOnce()
            snapshot.getValue(Int::class.java)
        } catch (e: Exception) {
            null}
    }

// Обновление максимума
    override suspend fun updateMaxResultByUserId(uId: String, newMaxScore: Int): Boolean {
        return try {
            FirebaseDatabase.getInstance().reference
                .child(NODE_USERS)
                .child(uId)
                .child(CHILD_CURRENT_MAX_SCORE)
                .setValue(newMaxScore)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun isAdmin(userId: String): Boolean {
        return try {
            (FirebaseDatabase.getInstance().reference
                .child(NODE_USERS)
                .child(userId)
                .child(CHILD_IS_ADMIN)
                .getDataOnce()
                .value ?: false).toString().toBoolean()
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