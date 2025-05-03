package com.leoevg.geoquiz.screens.question
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.leoevg.geoquiz.data.manager.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class QuestionScreenViewModel @Inject constructor(
    private val prefManager: SharedPrefManager
) : ViewModel() {
    var isSilentModeEnabled by mutableStateOf(prefManager.getBoolValueByKey("musicWorks", true))
    var selectedAnswerOptionId by mutableIntStateOf(-1)

    fun onEvent(event: QuestionScreenEvent){
        // SOLID
        when(event){
            QuestionScreenEvent.ApplyBtnClicked -> onApplyBtnClicked()
            QuestionScreenEvent.FinishBtnClicked -> onFinishBtnClicked()
            QuestionScreenEvent.HintBtnClicked -> onHintBtnClicked()
            QuestionScreenEvent.ImageDoubleClicked -> onImageDoubleBtnClicked()
            QuestionScreenEvent.NightModeBtnClicked -> onNightModeBtnClicked()
            is QuestionScreenEvent.OptionSelected -> onOptionSelected(event.selectedOptionId)
            QuestionScreenEvent.SilentModeBtnClicked -> onSilentModeBtnClicked()
        }
    }

    private fun onSilentModeBtnClicked(){
        val isSilentModeEnable = prefManager.getBoolValueByKey("musicWorks", true)
        prefManager.putBoolValue("musicWorks", !isSilentModeEnable)// смена режима
        isSilentModeEnabled = !isSilentModeEnable
    }

    private fun onApplyBtnClicked(){

    }
    private fun onFinishBtnClicked (){

    }
    private fun onHintBtnClicked(){

    }
    private fun onImageDoubleBtnClicked(){

    }
    private fun onNightModeBtnClicked(){

    }
    private fun onOptionSelected(selectedOptionId: Int){
        selectedAnswerOptionId = selectedOptionId
    }



}