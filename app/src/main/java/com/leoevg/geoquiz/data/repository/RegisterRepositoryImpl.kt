package com.leoevg.geoquiz.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.data.util.getCompletedResult
import com.leoevg.geoquiz.domain.repository.RegisterRepository

class RegisterRepositoryImpl : RegisterRepository {
    override suspend fun register(nickname: String, email: String, password: String): AuthResult? {
        // поменять под firebase
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(nickname, email, password).
            getCompletedResult()
    }
}