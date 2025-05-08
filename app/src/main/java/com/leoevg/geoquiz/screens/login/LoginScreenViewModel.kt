package com.leoevg.geoquiz.screens.login

import android.R.attr.password
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel( ) {
    // state вьюхи
    var state by mutableStateOf(LoginScreenState())

    fun onEvent(event: LoginScreenEvent){
        // SOLID
        when(event){
            // is - проверка является ли обьект евент объектом класса, а не сравнит его в тупую
            // при этом LoginBtnClicked - являются data object издесь мы сравниваем объекты без проблем
            is LoginScreenEvent.EmailChanged -> onEmailChanged(event.email)
            is LoginScreenEvent.PasswordChanged -> onPasswordChanged(event.password)
            LoginScreenEvent.LoginBtnClicked -> onLoginBtnClicked()
        }
    }

    private fun onEmailChanged(email: String){
        state = state.copy(email = email)
    }
    private fun onPasswordChanged(password: String){
        state = state.copy(password = password)
    }

    private fun onLoginBtnClicked(){
        if (state.email.isEmpty() || state.password.isEmpty()) {
            state.error = "Fields cannot be empty"
            return
        }
        state.error = null
        login(state.email, state.password)
    }

    private fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        // внутри этой ф-ции запрос к FIREBASE и его обработка. уйдет в repository
        state = state.copy(isLoading = true) // анимация полосы загрузки включается
        val result = authRepository.login(email, password)
        state = state.copy(isLoading = false) //  анимация полосы загрузки выключается

        result?.user?.let {
            state = state.copy(isLoggedIn = true)
        } ?: run {
            state.error = "Error signing in. Check your credentials"
        }
    }

}