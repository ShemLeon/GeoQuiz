package com.leoevg.geoquiz.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.update


@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel ( ) {
    private val _state = MutableStateFlow(RegisterScreenState())
    val state: StateFlow<RegisterScreenState> = _state

    fun onEvent(event: RegisterScreenEvent){
        // SOLID
        when(event){
            is RegisterScreenEvent.NicknameChanged -> onNicknameChanged(event.nickname)
            is RegisterScreenEvent.EmailChanged -> onEmailChanged(event.email)
            is RegisterScreenEvent.PasswordChanged -> onPasswordChanged(event.password)
            RegisterScreenEvent.RegisterBtnClicked -> onRegisterBtnClicked()
        }
    }

    private fun onNicknameChanged(nickname: String){
        _state.update { it.copy(nickname = nickname)}
    }
    private fun onEmailChanged(email: String){
        _state.update { it.copy(email = email) }
    }
    private fun onPasswordChanged(password: String){
        _state.update { it.copy(password= password)}
    }

    private fun onRegisterBtnClicked(){
        if (state.value.email.isEmpty() || state.value.password.isEmpty() || state.value.nickname.isEmpty()){
            _state.update { it.copy(error = "Fields cannot be empty") }
            return
        }
        _state.update { it.copy(error = null) }
        register(state.value.nickname, state.value.email, state.value.password)
    }

    private fun register(nickname: String, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO){
        // внутри этой ф-ции запрос к FIREBASE и его обработка. уйдет в repository
            _state.update{it.copy(isLoading = true)} // анимация полосы загрузки включается
            val result = authRepository.register(nickname, email, password)
            _state.update{it.copy(isLoading = false)} // анимация полосы загрузки включается
        result?.user?.let {
            _state.update{it.copy(isRegisteredIn = true)}
        } ?: run {
            _state.update{it.copy(error = "Error signing in. Check your credentials")}
        }
    }



}