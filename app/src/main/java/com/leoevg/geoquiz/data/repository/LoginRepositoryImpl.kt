package com.leoevg.geoquiz.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.data.util.getCompletedResult
import com.leoevg.geoquiz.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(email: String, password: String): AuthResult? {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).
            getCompletedResult()
    }
}