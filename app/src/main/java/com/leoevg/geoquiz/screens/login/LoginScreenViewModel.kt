package com.leoevg.geoquiz.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.data.repository.LoginRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.annotation.meta.When
import kotlin.String

class LoginScreenViewModel: ViewModel( ) {
    // state вьюхи
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)

    fun onEvent(event: LoginScreenEvent){
        // SOLID
        when(event){
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

    private fun login(email: String, password: String) {
        val loginRepository = LoginRepositoryImpl()

        viewModelScope.launch(Dispatchers.IO) {
            val result = loginRepository.login(email, password)

        }
    }

}