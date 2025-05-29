package com.leoevg.geoquiz.domain.repository

import com.google.firebase.auth.AuthResult

interface UserRepository {
    // ф-я suspend - т.к. была асинхронная таска в FIREBASE
    suspend fun getMaxResultByUserId(email: String): Double?
    suspend fun updateMaxResultByUserId(email: String, newMaxScore: Double): Boolean
}

