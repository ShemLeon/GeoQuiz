package com.leoevg.geoquiz.screens.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leoevg.geoquiz.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishScreenViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){
    private val _maxscore = MutableStateFlow<Int?>(null)
    val maxScore: StateFlow<Int?> = _maxscore

    fun loadMaxScore(){
        viewModelScope.launch {
            _maxscore.value = userRepository.getMaxResultByUserId("")
        }
    }


}