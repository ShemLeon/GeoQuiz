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

/* смысл getAsMap - у нас бд json. все элементы структуры мэп сохраняются по правилу key:value
        удобно использовать с нашей бд(тоже json). id не сохраняем - т.к. это key.
        мы по кей валью сохраняем остальные параметры, которые передадим в бд через MAP
        to - слово связи ключа к значению. "nickname" to nickname,
*/
