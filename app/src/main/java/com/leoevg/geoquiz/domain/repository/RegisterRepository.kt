package com.leoevg.geoquiz.domain.repository

import com.google.firebase.auth.AuthResult

interface RegisterRepository {
    // ф-я suspend - т.к. была асинхронная таска в FIREBASE
    suspend fun register(nickname: String, email: String, password: String):  RegisterResult?
    // заменить на REGISTER RESULT из FIREBASE
}