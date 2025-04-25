package com.leoevg.geoquiz.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.annotation.meta.When
import kotlin.String

class LoginScreenViewModel: ViewModel( ) {
    // state вьюхи
    var email by mutableStateOf("")
    var password by mutableStateOf("")

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

    private fun  onLoginBtnClicked(){

    }



}