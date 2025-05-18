package com.leoevg.geoquiz.data.model

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


/***
 *  getAsMap - у нас БД json. Все элементы структуры MAP сохраняются по правилу [key:value]
удобно использовать с нашей бд(тоже json).
id не сохраняем - это общий key.
По [key:value] сохраняем остальные параметры, которые передадим в БД через MAP
to - слово связи ключа к значению. "nickname" to nickname и т.д.
 ***/

