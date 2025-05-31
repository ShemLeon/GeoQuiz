package com.leoevg.geoquiz.screens.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class FinishScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){
    private val _maxscore = MutableStateFlow<Int?>(null)
    val maxScore: StateFlow<Int?> = _maxscore

    fun proceedMaxScore(finalScore: Int){
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            viewModelScope.launch(Dispatchers.IO) {
                _maxscore.value = userRepository.getMaxResultByUserId(
                    it
                // it - текущий uid
                )
                updateMaxScore(it, finalScore)
            }
        }
    }

    // Сохранить результат как максимальный, если он больше текущего максимума
    private suspend fun updateMaxScore(uid: String, finalScore: Int) {
        val currentMaxScore = userRepository.getMaxResultByUserId(
            uid
        ) ?: 0 // в случае null мы поставим 0
        if (finalScore > currentMaxScore) {
            userRepository.updateMaxResultByUserId(uid, finalScore)
        }

    }


}