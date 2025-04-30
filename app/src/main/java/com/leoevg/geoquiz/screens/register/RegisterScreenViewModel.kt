package com.leoevg.geoquiz.screens.register
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
class RegisterScreenViewModel @Inject constructor(
    private val registerRepository: AuthRepository
): ViewModel ( ) {
    // state вьюхи
    var nickname by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var isRegisteredIn by mutableStateOf(false)

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

    private fun onRegisterBtnClicked(){
        if (email.isEmpty() || password.isEmpty() || nickname.isEmpty()){
            error = "Fields cannot be empty"
            return
        }
        error = null
        register(nickname, email, password)
    }

    private fun register(nickname: String, email: String, password: String) = viewModelScope.launch(Dispatchers.IO){
        // внутри этой ф-ции запрос к FIREBASE и его обработка. уйдет в repository\
        isLoading = true // анимация полосы загрузки включается
        val result = registerRepository.register(nickname, email, password)
        isLoading = false //  анимация полосы загрузки выключается

        result?.user?.let {
            isRegisteredIn = true
        } ?: run {
            error = "Error signing in. Check your credentials"
        }
    }



}