package com.leoevg.geoquiz.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.util.getCompletedResult
import com.leoevg.geoquiz.domain.repository.LoginRepository

class LoginRepositoryImpl : LoginRepository {
    override suspend fun login(email: String, password: String): AuthResult? {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).
            getCompletedResult()
    }

    override suspend fun register(
        nickname: String,
        email: String,
        password: String
    ): AuthResult? {
        // ?
        val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .getCompletedResult()
        val uid = result?.user?.uid
        if (result == null) return null
        FirebaseDatabase.getInstance().reference.child("Users").child(uid).child("email").setValue(email)

        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {  }.getCompletedResult()
    }



}