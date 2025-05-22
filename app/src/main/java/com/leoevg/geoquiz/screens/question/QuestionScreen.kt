package com.leoevg.geoquiz.screens.question

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.AnswerOptionItem
import com.leoevg.geoquiz.ui.theme.Blue
import kotlin.Unit
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leoevg.geoquiz.data.model.TypeGame
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import org.checkerframework.checker.units.qual.Length

@Composable
fun QuestionScreen(
    question: Question,
    navigate: (NavigationPaths) -> Unit,
    typeGame: TypeGame,
    currentScore: Double,
    updateScore: (Double) -> Unit,
    viewModel: QuestionScreenViewModel = hiltViewModel<QuestionScreenViewModel, QuestionScreenViewModel.QuestionScreenViewModelFactory> { factory ->
        factory.create(question, typeGame, updateScore, navigate)
    }
){
// преобразование stateFlow в обычный для composable
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LaunchedEffect(state.error) {
        state.error?.let {
            Toast.makeText(context, "Pick smth", Toast.LENGTH_LONG).show()
        }
    }

    GeoQuizTheme(darkTheme = state.isNightModeEnabled) {
        QuestionScreenContent(
            question = question,
            currentScore = currentScore,
            state = state,
            onEvent = viewModel::onEvent
        )
    }
}

@Composable
fun QuestionScreenContent(
    modifier: Modifier = Modifier,
    question: Question,
    currentScore: Double,
    state: QuestionScreenState = QuestionScreenState(),
    onEvent: (QuestionScreenEvent) -> Unit
){
    val context = LocalContext.current  // context for hint
// Цвет фона
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = stringResource(R.string.your_score),
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "0 ${stringResource(R.string.val_points)}",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Row (
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text =  "${stringResource(R.string.question)} 1",
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
// Silent & DarkMode
            Row (
                modifier = Modifier.height(40.dp), // фиксированная высота Row с иконками
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(
                        if (state.isSilentModeEnabled)
                            R.drawable.volume_up
                        else
                            R.drawable.volume_down
                    ),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "volumeUp_icon_button",
                    modifier = Modifier
                        .aspectRatio(1f) // Сохраняем пропорции (квадрат)
                        .clickable{
                            onEvent(QuestionScreenEvent.SilentModeBtnClicked)

                        }
                )
                Icon(
                    painter = painterResource(
                        if (state.isNightModeEnabled)
                            R.drawable.icon_light_mode
                        else
                            R.drawable.icon_dark_mode
                    ),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "light_mode_icon",
                    modifier = Modifier.clickable{
                        onEvent(QuestionScreenEvent.NightModeBtnClicked)
                    }
                )
            }
        }
// Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // расположение квадратиком
                .padding(top=30.dp)
        ){
            AsyncImage(
                model = question.picturesUrls[0],
                contentDescription = "Question image",
                modifier = Modifier
                    .fillMaxSize()
            )
// Hint
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(6.dp)
                    .size(60.dp),
                contentPadding = PaddingValues(vertical = 5.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue.copy(alpha = 0.5f)
                ),
                onClick = {
                    onEvent(QuestionScreenEvent.HintBtnClicked)
                    Toast.makeText(context, "Hint: ${question.hint}", Toast.LENGTH_LONG).show()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.hint_button),
                    tint = Color.Black,
                    contentDescription = "hint_icon_button",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = stringResource(R.string.choose_correct_answer),
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 10.dp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
// Grid
        OptionAnswersSection(
            modifier = Modifier.padding(top=15.dp),
            answerOptions = question.answerOptions,
            selectedAnswerOption = state.selectedAnswer,
            isAnswerRight = state.isAnswerRight,
            // нам надо передать события, что произошло и передать в него выбранный id
            onItemSelected = {
                    optionAnswer -> onEvent(QuestionScreenEvent.OptionSelected(optionAnswer))
            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .height(56.dp)
            ){
// Apply
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = BlueGrey
//                    ),
                    contentPadding = PaddingValues(vertical = 15.dp),
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        onEvent(QuestionScreenEvent.ApplyBtnClicked)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.apply_button),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "hint_icon_button",
                        modifier = Modifier.size(24.dp),

                    )
                    Text(
                        stringResource(R.string.apply),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 10.dp),
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
// Finish
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(vertical = 10.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    onEvent(QuestionScreenEvent.FinishBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.finish),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.background
                )
            }
        }
    }

}


@Composable
fun OptionAnswersSection(
    modifier: Modifier = Modifier,
    answerOptions: List<AnswerOption>,
    selectedAnswerOption: String?,
    isAnswerRight: Boolean?,
    onItemSelected: (String) -> Unit,

){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(((answerOptions.size / 2 + answerOptions.size % 2) * 80).dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.Start,
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(answerOptions){currentOptionItem ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f) // Ограничиваем ширину элемента внутри ячейки
                    .padding(horizontal = 3.dp, vertical = 1.dp)
            ){
                AnswerOptionItem(
                    answerOption = currentOptionItem,
                    isSelected = selectedAnswerOption == currentOptionItem.optAnswer,
                    onClick = {
                        // передает id нового выбранного item.
                        onItemSelected(currentOptionItem.optAnswer)
                    }
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun QuestionScreenPreview() {
    GeoQuizTheme(
        darkTheme = false
    ){
    QuestionScreenContent(
        question = Question(
            id = 1,
            rightAnswer = "Ответ 1",
            hint = "Тестовая подсказка",
            answerOptions = listOf(
                AnswerOption(1, "Ответ 1"),
                AnswerOption(2, "Отт 2"),
                AnswerOption(3, "Ответ 3"),
                AnswerOption(4, "Ответ 4"),
                AnswerOption(5, "Ответ 5"),
                AnswerOption(6, "Ответ 6")
            ),
            picturesUrls = listOf("https://picsum.photos/400/400")
        ),
        currentScore = 10.0
    ) { }
}}


@Composable
@Preview(showBackground = true)
fun QuestionScreenDarkPreview() {
    GeoQuizTheme(
        darkTheme = true
    ){
    QuestionScreenContent(
        question = Question(
            id = 1,
            rightAnswer = "Ответ 1",
            hint = "Тестовая подсказка",
            answerOptions = listOf(
                AnswerOption(1, "Ответ 1"),
                AnswerOption(2, "Отт 2"),
                AnswerOption(3, "Ответ 3"),
                AnswerOption(4, "Ответ 4"),
                AnswerOption(5, "Ответ 5"),
                AnswerOption(6, "Ответ 6")
            ),
            picturesUrls = listOf("https://picsum.photos/400/400")
        ),
        currentScore = 10.0
    ) { }
}}

