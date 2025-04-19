package com.leoevg.geoquiz.screens.question

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuestionScreenViewModel : ViewModel() {
    var selectedAnswerOptionId by mutableIntStateOf(-1)

    fun onEvent(event: QuestionScreenEvent) {
        when (event) {
            QuestionScreenEvent.ApplyBtnClicked -> onApplyBtnClicked()
            QuestionScreenEvent.FinishBtnClicked -> onFinishBtnClicked()
            QuestionScreenEvent.HintBtnClicked -> onHintBtnClicked()
            QuestionScreenEvent.ImageDoubleClicked -> onImageDoubleClicked()
            QuestionScreenEvent.NightModeBtnClicked -> onNightModeBtnClicked()
            is QuestionScreenEvent.OptionSelected -> onOptionSelected(event.selectedOptionId)
            QuestionScreenEvent.SilentModeBtnClicked -> onSilentModeBtnClicked()
        }
    }

    private fun onApplyBtnClicked() {

    }
    private fun onFinishBtnClicked() {

    }
    private fun onHintBtnClicked() {

    }
    private fun onImageDoubleClicked() {

    }
    private fun onNightModeBtnClicked() {

    }
    private fun onOptionSelected(selectedOptionId: Int) {
        selectedAnswerOptionId = selectedOptionId
    }
    private fun onSilentModeBtnClicked() {

    }
}