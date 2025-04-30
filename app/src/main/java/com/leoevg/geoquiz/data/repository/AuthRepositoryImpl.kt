package com.leoevg.geoquiz.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.leoevg.geoquiz.data.model.User
import com.leoevg.geoquiz.data.util.getCompletedResult
import com.leoevg.geoquiz.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(email: String, password: String): AuthResult? {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).
            getCompletedResult()
    }

    override suspend fun register(
        nickname: String,
        email: String,
        password: String
    ): AuthResult? {
        // возвращает null able - null "возможный" объект аутхрезалт
        val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .getCompletedResult()

        val uid = (result?.user?.uid) ?: return null

        val user = User(uid, nickname, email)


        // если что-то из result user uid - нул, то вернет нул. это safe call
        FirebaseDatabase.getInstance().reference.child("Users").child(uid)
            //.child("email").setValue(email)
            .setValue(user.getAsMap())
        return result
    }



}