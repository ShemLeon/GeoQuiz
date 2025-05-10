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
    private val authRepository: AuthRepository
): ViewModel ( ) {
    // state вьюхи
    // state по умолчанию (для возможности тестирования и @preview)
    // обеспечивает реальное и наглядное изменение данных в модели и рекомпозицию UI
    var state by mutableStateOf(RegisterScreenState(

    ))

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
        state = state.copy(nickname = nickname)
    }
    private fun onEmailChanged(email: String){
        state = state.copy(email = email)
    }
    private fun onPasswordChanged(password: String){
        state= state.copy(password= password)
    }

    private fun onRegisterBtnClicked(){
        if (state.email.isEmpty() || state.password.isEmpty() || state.nickname.isEmpty()){
            state.error = "Fields cannot be empty"
            return
        }
        state.error = null
        register(state.nickname, state.email, state.password)
    }

    private fun register(nickname: String, email: String, password: String) =
        viewModelScope.launch(Dispatchers.IO){
        // внутри этой ф-ции запрос к FIREBASE и его обработка. уйдет в repository

        state = state.copy(isLoading = true) // анимация полосы загрузки включается
        val result = authRepository.register(nickname, email, password)
        state = state.copy(isLoading = false) // анимация полосы загрузки включается

        result?.user?.let {
            state = state.copy(isRegisteredIn = true)
        } ?: run {
            state.error = "Error signing in. Check your credentials"
        }
    }



}