package com.leoevg.geoquiz.screens.question

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuestionScreenViewModel: ViewModel() {
    var selectedAnswerOptionId by mutableIntStateOf(-1)

    fun onEvent(event: QuestionScreenEvent){
        // SOLID
    }

}