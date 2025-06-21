package com.leoevg.geoquiz.screens.admin

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.domain.repository.FirebaseStorageRepository
import com.leoevg.geoquiz.domain.repository.SuggestionRepository
import com.leoevg.geoquiz.screens.login.LoginScreenEvent
import com.leoevg.geoquiz.screens.login.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AdminScreenViewModel @Inject constructor(
    private val firebaseStorage: FirebaseStorageRepository,
    private val suggestionRepository: SuggestionRepository
): ViewModel(){
    private val _state = MutableStateFlow(AdminScreenState())
    val state: StateFlow<AdminScreenState> = _state
    val snackbarHostState = SnackbarHostState()

    fun onEvent(event: AdminScreenEvent){
        // SOLID
        when(event){
            is AdminScreenEvent.SuggestedTypeGame -> onSuggestedTypeGameChanged(event.typeGame)
            is AdminScreenEvent.SuggestedCountryName -> onSuggestedCountryName(event.countryName)
            AdminScreenEvent.RejectSuggestionClicked -> onRejectBtnClicked()
            AdminScreenEvent.ApproveBtnClicked -> onApproveBtnClicked()
            AdminScreenEvent.ChooseGameModeDialogDismissed -> onChooseGameModeDialogDismissed()
            is AdminScreenEvent.ChooseGameModeDialogModesSelected -> onChooseGameModeDialogModesSelected(event.selectedModes)
        }
    }

    private fun onChooseGameModeDialogModesSelected(selectedModes: List<TypeGame>) {
        _state.update { it.copy(isChooseGameModeDialogRequested = false) }
        // TODO: apply suggestion
    }

    private fun onChooseGameModeDialogDismissed() {
        _state.update { it.copy(isChooseGameModeDialogRequested = false) }
    }

    private fun onRejectBtnClicked(){
        if (state.value.currentSuggestion == null) return
        deleteSuggestion(state.value.currentSuggestion!!.id)
    }

    private fun onSuggestedTypeGameChanged(typeGame: String){
        _state.update { it.copy(typeGame = typeGame) }
    }
    private fun onSuggestedCountryName(countryName: String){
        _state.update { it.copy(countryName = countryName) }
    }

    private fun onApproveBtnClicked(){
        _state.update { it.copy(isChooseGameModeDialogRequested = true) }
    }

    private fun loadSuggestions(){
        viewModelScope.launch(Dispatchers.IO) {

            val list = suggestionRepository.loadSuggestions()
            _state.update {
                it.copy(
                    suggestionsList = list,
                    currentSuggestion = list[0]
                )
            }
        }
    }

    private fun deleteSuggestion(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            suggestionRepository.deleteSuggestion(id)
            val suggestionsList = _state.value.suggestionsList.toMutableList()
            suggestionsList.removeAt(0)
            val nextSuggestion = suggestionsList.firstOrNull()
            _state.update {
                it.copy(
                    suggestionsList = suggestionsList,
                    currentSuggestion = nextSuggestion
                )
            }
        }
    }



    init {
        loadSuggestions()
    }
}

