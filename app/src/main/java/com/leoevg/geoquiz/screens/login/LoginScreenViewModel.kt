package com.leoevg.geoquiz.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.data.repository.LoginRepositoryImpl
import com.leoevg.geoquiz.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.annotation.meta.When
import javax.inject.Inject
import kotlin.String

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel( ) {
    // state вьюхи
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)
    var isLoggedIn by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

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
        this.email = email
    }
    private fun onPasswordChanged(password: String){
        this.password = password
    }

    private fun onLoginBtnClicked(){
        if (email.isEmpty() || password.isEmpty()) {
            error = "Fields cannot be empty"
            return
        }
        error = null
        login(email, password)
    }

    private fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        // внутри этой ф-ции запрос к FIREBASE и его обработка. уйдет в repository
        isLoading = true
        val result = loginRepository.login(email, password)
        isLoading = false

        result?.user?.let {
            isLoggedIn = true
        } ?: run {
            error = "Error signing in. Check your credentials"
        }
    }

}