package com.leoevg.geoquiz.domain.repository

import com.google.firebase.auth.AuthResult

interface UserRepository {
    // ф-я suspend - т.к. была асинхронная таска в FIREBASE
    suspend fun getMaxResultByUserId(uId: String): Int?
    suspend fun updateMaxResultByUserId(uId: String, newMaxScore: Int): Boolean
}

