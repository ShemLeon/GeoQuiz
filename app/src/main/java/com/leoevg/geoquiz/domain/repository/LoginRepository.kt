package com.leoevg.geoquiz.domain.repository

import com.google.firebase.auth.AuthResult

interface LoginRepository {
    // ф-я suspend - т.к. была асинхронная таска в FIREBASE
    suspend fun login(email: String, password: String): AuthResult?
    suspend fun register(nickname: String, email: String, password: String):  AuthResult?
}