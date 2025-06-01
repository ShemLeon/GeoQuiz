package com.leoevg.geoquiz.screens.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _maxScore = MutableStateFlow<Int?>(null)
    val maxScore: StateFlow<Int?> = _maxScore.asStateFlow()

    fun proceedMaxScore(finalScore: Int) {
        val isPreviewMode = userRepository is FakeUserRepositoryForPreview
        val currentUid: String? = if (isPreviewMode) {
            "fake_preview_user_id"
        } else {
            FirebaseAuth.getInstance().currentUser?.uid
        }
        if (currentUid != null) {
            viewModelScope.launch(Dispatchers.IO) {
                // 1. Получаем текущий максимальный результат
                val currentMaxInDb = userRepository.getMaxResultByUserId(currentUid)
                _maxScore.value = currentMaxInDb // Обновляем Flow для UI
                // 2. Обновляем в БД, если новый результат лучше
                if (finalScore > (currentMaxInDb ?: 0)) {
                    userRepository.updateMaxResultByUserId(currentUid, finalScore)
                    // 3. После успешного обновления в БД, снова обновляем Flow новым значением
                    _maxScore.value = finalScore
                }
            }
        } else {
            if (isPreviewMode) {
                viewModelScope.launch(Dispatchers.IO) {
                    val previewMaxScore = userRepository.getMaxResultByUserId("fake_preview_user_id")
                    _maxScore.value = previewMaxScore
                    if (finalScore > (previewMaxScore ?: 0)) {
                        _maxScore.value = finalScore
                    }
                }
            } else {
                _maxScore.value = null
            }
        }
    }
}