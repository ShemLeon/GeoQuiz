package com.leoevg.geoquiz.screens.question

import androidx.lifecycle.ViewModel
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.manager.SharedPrefManager
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.service.AudioService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = QuestionScreenViewModel.QuestionScreenViewModelFactory::class)
class QuestionScreenViewModel @AssistedInject constructor(
    @Assisted private val question: Question,
    @Assisted private val typeGame: TypeGame,
    private val prefManager: SharedPrefManager,
    private val audioService: AudioService
) : ViewModel() {
    private val _state = MutableStateFlow(QuestionScreenState(
        isSilentModeEnabled = prefManager.getBoolValueByKey("darkWorks", true),
        isNightModeEnabled = prefManager.getBoolValueByKey("darkWorks", true)
        ))
    val state: StateFlow<QuestionScreenState> = _state

    fun onEvent(event: QuestionScreenEvent){
        // SOLID
        when(event){
            // по событиям из view (евенты) вызываем функции
            is QuestionScreenEvent.ApplyBtnClicked -> onApplyBtnClicked(event.question)
            QuestionScreenEvent.FinishBtnClicked -> onFinishBtnClicked()
            QuestionScreenEvent.HintBtnClicked -> onHintBtnClicked()
            QuestionScreenEvent.ImageDoubleClicked -> onImageDoubleBtnClicked()
            QuestionScreenEvent.NightModeBtnClicked -> onNightModeBtnClicked()
            is QuestionScreenEvent.OptionSelected -> onOptionSelected(event.selectedOption)
            QuestionScreenEvent.SilentModeBtnClicked -> onSilentModeBtnClicked()
        }
    }
    private fun onApplyBtnClicked(question: Question){
        if (state.value.selectedAnswer == "") {
            _state.update { it.copy(error = "ВЫБЕРИ СКА") }
            return
        }

        if (state.value.selectedAnswer == question.rightAnswer){
//            var score: Double =
//            if (state.value.isHintUsed){
//
//            }

            // TODO Hint - проверить state (создать его) isHintUsed: Boolean
            // TODO +score
            // TODO сбросить флаг hint на false
        } else {
            _state.update { it.copy(isAnswerRight = false) }
            // TODO color red - TIME PAUSE & NEXT SCREEN
            _state.update { it.copy(error = "UNKNOWN ERROR") }
            return
        }

    }

    private fun onNightModeBtnClicked(){
        val isNightModeEnable = prefManager.getBoolValueByKey("darkWorks", true)
        prefManager.putBoolValue("darkWorks", !isNightModeEnable)
        // state = state.copy(isNightModeEnabled = !isNightModeEnable)
        _state.update {
            it.copy(isNightModeEnabled = !isNightModeEnable)
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


    private fun onFinishBtnClicked (){
    // Play sound only if !isSilentModeEnable
        if (_state.value.isSilentModeEnabled){
            audioService.playSound(R.raw.tadam)
        }
    }

    private fun onHintBtnClicked(){
        if (_state.value.isHintUsed){
            _state.update {
                it.copy(isHintUsed = true)
            }
        }
    }

    private fun onImageDoubleBtnClicked(){

    }

    private fun onOptionSelected(selectedOption: String){
        _state.update {
            it.copy(selectedAnswer = selectedOption)
        }
    }

    @AssistedFactory
    interface QuestionScreenViewModelFactory {
        fun create(question: Question, typeGame: TypeGame): QuestionScreenViewModel
    }
}