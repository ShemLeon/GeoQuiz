package com.leoevg.geoquiz.screens.login

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel( ) {
    // state вьюхи
    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state
    val snackbarHostState = SnackbarHostState()

    fun onEvent(event: LoginScreenEvent){
        // SOLID
        when(event){
            // is - проверка является ли обьект евент объектом класса, а не сравнит его в тупую
            // при этом LoginBtnClicked - являются data object и здесь мы сравниваем объекты без проблем
            is LoginScreenEvent.EmailChanged -> onEmailChanged(event.email)
            is LoginScreenEvent.PasswordChanged -> onPasswordChanged(event.password)
            LoginScreenEvent.LoginBtnClicked -> onLoginBtnClicked()
        }
    }

    private fun onEmailChanged(email: String){
        _state.update { it.copy(email = email) }
    }

    private fun onPasswordChanged(password: String){
        _state.update{ it.copy(password = password) }
    }

    private fun onLoginBtnClicked(){
        if (state.value.email.isEmpty() || state.value.password.isEmpty()) {
            viewModelScope.launch {
                _state.update { it.copy(snackbarMessage = "Fields cannot be empty") }
            }
            return
        }
        _state.update{it.copy(error = null)}
        login(state.value.email, state.value.password)
    }

    fun clearSnackbarMessage() {
        _state.update { it.copy(snackbarMessage = null) }
    }

    private fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        // внутри этой ф-ции запрос к FIREBASE и его обработка. уйдет в repository
        _state.update{it.copy(isLoading = true)} // анимация полосы загрузки включается
        val result = authRepository.login(email, password)
        _state.update{it.copy(isLoading = false)} //  анимация полосы загрузки выключается

        result?.user?.let {
            _state.update{it.copy(isLoggedIn = true)}
        } ?: run {
            _state.update{it.copy(snackbarMessage = "Error signing in. Check your credentials")}
        }
    }

}