package com.leoevg.geoquiz.screens.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChooseScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _isAdmin = MutableStateFlow<Boolean>(false)
    val isAdmin = _isAdmin.asStateFlow()

    fun checkIfUserIsAdmin() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        viewModelScope.launch(Dispatchers.IO) {
            val isUserAnAdmin = userRepository.isAdmin(userId)
            _isAdmin.update { isUserAnAdmin }
        }
    }

    init {
        checkIfUserIsAdmin()
    }
}