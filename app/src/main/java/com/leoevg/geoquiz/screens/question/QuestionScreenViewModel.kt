package com.leoevg.geoquiz.screens.question

import androidx.lifecycle.ViewModel
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.manager.SharedPrefManager
import com.leoevg.geoquiz.data.service.AudioService
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class QuestionScreenViewModel @Inject constructor(
    private val prefManager: SharedPrefManager,
    private val audioService: AudioService
) : ViewModel() {
    private val _state = MutableStateFlow(QuestionScreenState(
        isSilentModeEnabled = prefManager.getBoolValueByKey("darkWorks", true),
        isNightModeEnabled =prefManager.getBoolValueByKey("darkWorks", true)
        ))
    val state: StateFlow<QuestionScreenState> = _state

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
        prefManager.putBoolValue("musicWorks", !isSilentModeEnable) // change silent mode
        _state.update { it.copy(isSilentModeEnabled = !isSilentModeEnable) }

        // Play sound only if !isSilentModeEnable
        if (state.value.isSilentModeEnabled){
            // Play music from resource
            audioService.playSound(R.raw.silent_klav)
        }
    }

    override fun onCleared() {
        // освобождение ресурсов
        super.onCleared()
        audioService.releaseMediaPlayer()
    }

    private fun onNightModeBtnClicked(){
        val isNightModeEnable = prefManager.getBoolValueByKey("darkWorks", true)
        prefManager.putBoolValue("darkWorks", !isNightModeEnable)
        // state = state.copy(isNightModeEnabled = !isNightModeEnable)
        _state.update {
            it.copy(isNightModeEnabled = !isNightModeEnable)
        }
    }

    private fun onApplyBtnClicked(){

    }

    private fun onFinishBtnClicked (){
    // Play sound only if !isSilentModeEnable
        if (_state.value.isSilentModeEnabled){
            audioService.playSound(R.raw.tadam)
        }
    }

    private fun onHintBtnClicked(){
       // Toast.makeText(context, "Hint: ${question.hint}", Toast.LENGTH_LONG).show()
    }

    private fun onImageDoubleBtnClicked(){

    }

    private fun onOptionSelected(selectedOptionId: Int){
        _state.update {
            it.copy(selectedAnswerOptionId = selectedOptionId)
        }
    }



}