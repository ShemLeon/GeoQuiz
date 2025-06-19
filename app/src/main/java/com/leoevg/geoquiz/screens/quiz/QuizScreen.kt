package com.leoevg.geoquiz.screens.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.question.QuestionScreen
import com.leoevg.geoquiz.ui.components.LoadingDialog


// Добавь эти импорты в начало файла
import androidx.compose.ui.platform.LocalContext
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.data.model.typeGames

@Composable
fun QuizScreen(
    typeGame: TypeGame,
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
){
    val context = LocalContext.current
    val viewModel = hiltViewModel<QuizScreenViewModel>()
    var currentScore by remember { mutableIntStateOf(0) }


    // блок запустится 1 раз для загрузки квиза с определенным typeGame
    LaunchedEffect(Unit) {
        viewModel.loadQuiz(context.getString(typeGame.typeGameNameResId))
    }

    // обращаемся к viewModel, получаем currentQuiz и делаем let блок -
    // если все ок - открываем QuestionScreen и передаем туда контент
    viewModel.currentQuiz?.let { quiz ->
        QuestionScreen(
            question = quiz.questions[viewModel.currentQuestionIndex],
            navigate = navigate,
            typeGame = typeGame,
            currentScore = currentScore,
            currentQuestionNumber = viewModel.currentQuestionIndex + 1,
            updateScore = { newScore ->
                currentScore = newScore
            },
            openNextQuestion = {
                val result = viewModel.moveToNextQuestion()
                if (!result) {
                    popBackStack()
                    navigate(NavigationPaths.Finish(currentScore))
                }
            }
        )
    } ?: run {
        // если блок не загрузится - включаем заставку
        LoadingDialog(isLoading = true)
    }
}


@Composable
@Preview(showBackground = true)
fun QuizScreenPreview() {
    QuestionScreen(
        question = Question(
            id = "1",
            rightAnswer = "Ответ 1",
            hint = "Тестовая подсказка",
            answerOptions = listOf(
                AnswerOption(1, "Ответйцуйцу 321"),
                AnswerOption(2, "Отт 2"),
                AnswerOption(3, "Ответ 3"),
                AnswerOption(4, "Ответ 4"),
                AnswerOption(5, "Ответ 5"),
                AnswerOption(6, "Ответ 6")
            ),
            picturesUrls = listOf("https://picsum.photos/400/400")
        ),
        navigate = {},
        typeGame = typeGames[0],
        currentScore = 0,
        updateScore = {},
        currentQuestionNumber = 1,
        openNextQuestion = {  }
    )
}