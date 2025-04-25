package com.leoevg.geoquiz.domain.repository

import com.google.firebase.auth.AuthResult

interface LoginRepository {
    suspend fun login(email: String, password: String): AuthResult
}