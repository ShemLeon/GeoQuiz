package com.leoevg.geoquiz.screens.quiz

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.geoquiz.data.model.Quiz
import com.leoevg.geoquiz.domain.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val quizRepository: QuizRepository
): ViewModel() {
    // state вьюхи
    var currentQuiz by mutableStateOf<Quiz?>(null)
    var currentQuestionIndex by mutableIntStateOf(0)

// реализация получения вопросов
    fun loadQuiz(quizTypeGame: String) {
        viewModelScope.launch(Dispatchers.IO){
            currentQuiz = quizRepository.loadQuiz(quizTypeGame)
        }
    }
}