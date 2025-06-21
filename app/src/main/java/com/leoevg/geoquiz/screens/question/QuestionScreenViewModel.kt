package com.leoevg.geoquiz.screens.question

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.manager.SharedPrefManager
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.service.AudioService
import com.leoevg.geoquiz.domain.repository.UserRepository
import com.leoevg.geoquiz.navigation.NavigationPaths
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.Unit

@HiltViewModel(assistedFactory = QuestionScreenViewModel.QuestionScreenViewModelFactory::class)
class QuestionScreenViewModel @AssistedInject constructor(
    @Assisted private var question: Question,
    @Assisted private val typeGame: TypeGame,
    @Assisted private val updateScore: (Int) -> Unit,
    @Assisted private val navigate: (NavigationPaths) -> Unit,
    @Assisted private val openNextQuestion: () -> Unit,

    private val prefManager: SharedPrefManager,
    private val audioService: AudioService,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(QuestionScreenState(
        isSilentModeEnabled = prefManager.getBoolValueByKey("darkWorks", true),
        isNightModeEnabled = prefManager.getBoolValueByKey("darkWorks", true)
        ))
    val state: StateFlow<QuestionScreenState> = _state
    val snackbarHostState = SnackbarHostState()

    fun onEvent(event: QuestionScreenEvent){
        // SOLID
        when(event){
            // по событиям из view (евенты) вызываем функции
            QuestionScreenEvent.ApplyBtnClicked -> onApplyBtnClicked()
            QuestionScreenEvent.FinishBtnClicked -> onFinishBtnClicked()
            QuestionScreenEvent.HintBtnClicked -> onHintBtnClicked()
            QuestionScreenEvent.NightModeBtnClicked -> onNightModeBtnClicked()
            is QuestionScreenEvent.OptionSelected -> onOptionSelected(event.selectedOption)
            QuestionScreenEvent.SilentModeBtnClicked -> onSilentModeBtnClicked()
        }
    }

    private fun onApplyBtnClicked(){
        // отработка ошибки
        if (state.value.selectedAnswer == "") {
            _state.update { it.copy(error = "PICK SMTH!!") }
            return
        }

        // Основная адища
        val result = if (state.value.selectedAnswer == question.rightAnswer){
            var score: Int = typeGame.typeGameQuestionCost
            if (state.value.isHintUsed){
                score /= 2
            }
            score += state.value.currentScore

            updateScore(score)
            true
        } else {
            false
        }

        viewModelScope.launch {
            _state.update { it.copy(isAnswerRight = result) }
            delay(500)
            openNextQuestion()
        }
    }

    private fun onNightModeBtnClicked(){
        val isNightModeEnable = prefManager.getBoolValueByKey("darkWorks", true)
        prefManager.putBoolValue("darkWorks", !isNightModeEnable)
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


        navigate(NavigationPaths.Finish(finalScore = _state.value.currentScore))


    }

    private fun onHintBtnClicked(){
        viewModelScope.launch {
            snackbarHostState.showSnackbar(
                message = question.hint,
                actionLabel = "Got it!"
            )
        }
        if (!_state.value.isHintUsed){
            _state.update {
                it.copy(isHintUsed = true)
            }
        }
    }

    private fun onOptionSelected(selectedOption: String){
        _state.update {
            it.copy(selectedAnswer = selectedOption)
        }
    }

    fun updateState(question: Question, currentScore: Int) {
        _state.update { QuestionScreenState(
            isSilentModeEnabled = it.isSilentModeEnabled,
            isNightModeEnabled = it.isNightModeEnabled,
            currentScore = currentScore
        ) }
        this.question = question
    }

    @AssistedFactory
    // умеет создавать мою viewModel, замена DaggerHilt
    interface QuestionScreenViewModelFactory {
        fun create(
            question: Question,
            typeGame: TypeGame,
            updateScore: (Int) -> Unit,
            navigate: (NavigationPaths) -> Unit,
            openNextQuestion: () -> Unit
        ): QuestionScreenViewModel
    }
}



















