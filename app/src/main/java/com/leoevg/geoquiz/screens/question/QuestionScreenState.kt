package com.leoevg.geoquiz.screens.question

import com.leoevg.geoquiz.data.model.Question

data class QuestionScreenState (
    // val а не var - т.к. изменение через state Flow а не напрямую
    val isSilentModeEnabled: Boolean = true,
    val isNightModeEnabled: Boolean = true,
    val selectedAnswer: String = "",
    val error: String? = null,

    val isAnswerRight: Boolean? = null, // если мы еще не ответили - состояние null,
    // дальше смена на false/true
    val isHintUsed: Boolean = false,
    val currentScore: Int = 0

)


