package com.leoevg.geoquiz.screens.admin

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.leoevg.geoquiz.domain.repository.FirebaseStorageRepository
import com.leoevg.geoquiz.screens.login.LoginScreenEvent
import com.leoevg.geoquiz.screens.login.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AdminScreenViewModel @Inject constructor(
    private val firebaseStorage: FirebaseStorageRepository
): ViewModel(){
    private val _state = MutableStateFlow(AdminScreenState())
    val state: StateFlow<AdminScreenState> = _state
    val snackbarHostState = SnackbarHostState()

    fun onEvent(event: AdminScreenEvent){
        // SOLID
        when(event){
            is AdminScreenEvent.SuggestedTypeGame -> onSuggestedTypeGameChanged(event.typeGame)
            is AdminScreenEvent.SuggestedCountryName -> onSuggestedCountryName(event.countryName)
            AdminScreenEvent.ApproveBtnClicked -> onApproveBtnClicked()
            AdminScreenEvent.RejectBtnClicked -> onRejectBtnClicked()

        }
    }

    private fun onSuggestedTypeGameChanged(typeGame: String){
        _state.update { it.copy(typeGame = typeGame) }
    }
    private fun onSuggestedCountryName(countryName: String){
        _state.update { it.copy(countryName = countryName) }
    }

    private fun onApproveBtnClicked(){
        viewModelScope.launch {
            snackbarHostState.showSnackbar(
                message = "Fields cannot be empty",
                actionLabel = "Close"
            )
        }
        return
    }


    private fun onRejectBtnClicked(){
        viewModelScope.launch {
            snackbarHostState.showSnackbar(
                message = "Fields cannot be empty",
                actionLabel = "Close"
            )
        }
        return
    }
}

