package com.leoevg.geoquiz.screens.question
import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.manager.SharedPrefManager
import com.leoevg.geoquiz.data.service.AudioService
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class QuestionScreenViewModel @Inject constructor(
    private val prefManager: SharedPrefManager,
    private val audioService: AudioService
) : ViewModel() {
    var isSilentModeEnabled by mutableStateOf(prefManager.getBoolValueByKey("musicWorks", true))
    var isNightModeEnabled by mutableStateOf(prefManager.getBoolValueByKey("darkWorks", true))
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
        prefManager.putBoolValue("musicWorks", !isSilentModeEnable) // change silent mode
        isSilentModeEnabled = !isSilentModeEnable

        // Play sound only if !isSilentModeEnable
        if (isSilentModeEnabled){
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
        isNightModeEnabled = !isNightModeEnable
    }

    private fun onApplyBtnClicked(){


    }
    private fun onFinishBtnClicked (){
    // Play sound only if !isSilentModeEnable
        if (isSilentModeEnabled){
            // Play music from resource
            audioService.playSound(R.raw.tadam)
        }
    }
    private fun onHintBtnClicked(){
       // Toast.makeText(context, "Hint: ${question.hint}", Toast.LENGTH_LONG).show()
    }
    private fun onImageDoubleBtnClicked(){

    }

    private fun onOptionSelected(selectedOptionId: Int){
        selectedAnswerOptionId = selectedOptionId
    }



}