package com.leoevg.geoquiz.screens.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.question.QuestionScreen
import com.leoevg.geoquiz.ui.components.LoadingDialog

@Composable
fun QuizScreen(
    typeGame: String = "",
    navigate: (NavigationPaths) -> Unit
){
    val viewModel = hiltViewModel<QuizScreenViewModel>()
    LaunchedEffect(Unit) {
        viewModel.loadQuiz(typeGame)
    }

    viewModel.currentQuiz?.let { quiz ->
        QuestionScreen(
            question = quiz.questions[viewModel.currentQuestionIndex],
            navigate = navigate
        )
    } ?: run {
        LoadingDialog(isLoading = true)
    }
}




@Composable
@Preview(showBackground = true)
fun QuizScreenPreview(){
    QuizScreen {  }
}