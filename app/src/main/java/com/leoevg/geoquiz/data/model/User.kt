package com.leoevg.geoquiz.data.model

import androidx.compose.runtime.key

data class User(
    val id: String,
    val nickname: String,
    val email: String,
    var currentMaxScore: Int = 0

){
    fun getAsMap(): Map<String, Any>{
        return mapOf(
            "nickname" to nickname,
            "email" to email,
            "currentMaxScore" to currentMaxScore
        )
    }
}
