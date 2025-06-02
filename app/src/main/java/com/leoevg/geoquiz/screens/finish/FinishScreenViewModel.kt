package com.leoevg.geoquiz.screens.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.domain.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = FinishScreenViewModel.FinishScreenViewModelFactory::class)
class FinishScreenViewModel @AssistedInject constructor(
    @Assisted private val finalScore: Int,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FinishScreenState(finalScore = finalScore))
    val state: StateFlow<FinishScreenState> = _state.asStateFlow()

    fun proceedMaxScore() {
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            viewModelScope.launch(Dispatchers.IO) {
                // 1. Получаем текущий максимальный результат
                val currentMaxInDb = userRepository.getMaxResultByUserId(it)?:0
                _state.update { it.copy(maxScore = currentMaxInDb) }
                // 2.
                if (finalScore>currentMaxInDb) userRepository.updateMaxResultByUserId(it, finalScore)

            }
        }
    }

    @AssistedFactory
    interface FinishScreenViewModelFactory{
        fun create(finalScore: Int): FinishScreenViewModel
    }
}

