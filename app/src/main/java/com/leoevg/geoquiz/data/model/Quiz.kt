package com.leoevg.geoquiz.data.model

data class Quiz(
    // то что в круглых скобках - поля класса и конструктор
    val typeGame: String,
    val questions: List<Question>
)