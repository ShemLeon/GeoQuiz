package com.leoevg.geoquiz.screens.register
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.String

class RegisterScreenViewModel: ViewModel( ) {
    // state вьюхи
    var nickname by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

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
        this.nickname = nickname
    }
    private fun onEmailChanged(email: String){
        this.email = email
    }
    private fun onPasswordChanged(password: String){
        this.password = password
    }

    private fun  onRegisterBtnClicked(){

    }



}