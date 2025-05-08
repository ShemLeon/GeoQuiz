package com.leoevg.geoquiz.screens.question

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class QuestionScreenState (
    var isSilentModeEnabled: Boolean = true,
    var isNightModeEnabled: Boolean = true,
    var selectedAnswerOptionId: Int = -1
)