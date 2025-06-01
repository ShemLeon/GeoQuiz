package com.leoevg.geoquiz.data.model

import kotlin.String

data class Suggestion(
    val id: String,
    val country: String,
    val imageUrl: String
){
    fun getAsMap(): Map<String, Any>{
        return mapOf(
            "country" to country,
            "imageUrl" to imageUrl
        )
    }
}