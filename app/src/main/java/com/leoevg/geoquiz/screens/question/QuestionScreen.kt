package com.leoevg.geoquiz.screens.question

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.AnswerOption
import com.leoevg.geoquiz.data.model.Question
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.quiz.QuizScreen
import com.leoevg.geoquiz.ui.components.AnswerOptionItem
import com.leoevg.geoquiz.ui.theme.Blue
import com.leoevg.geoquiz.ui.theme.BlueGrey
import kotlin.Unit

@Composable
fun QuestionScreen(
    question: Question,
    navigate: (NavigationPaths) -> Unit
){
    val viewModel: QuestionScreenViewModel = viewModel()
    val context = LocalContext.current  // context for hint
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = stringResource(R.string.your_score),
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Text(
                text = "0 ${stringResource(R.string.val_points)}",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
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
                textAlign = TextAlign.Center
            )
            Row (
                modifier = Modifier.height(40.dp), // фиксированная высота Row с иконками
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(R.drawable.volume_up),
                    tint = Color.Black,
                    contentDescription = "volumeUp_icon_button",

                    modifier = Modifier

                        .aspectRatio(1f) // Сохраняем пропорции (квадрат)
                        .clickable{
                        viewModel.onEvent(QuestionScreenEvent.SilentModeBtnClicked)

                    }
                )
                Icon(
                    painter = painterResource(R.drawable.light_mode_icon),
                    tint = Color.Black,
                    contentDescription = "light_mode_icon",
                    modifier = Modifier.clickable{
                        viewModel.onEvent(QuestionScreenEvent.NightModeBtnClicked)
                    }
                )
            }
        }

        AsyncImage(
            model = question.picturesUrls[0],
            contentDescription = "Question image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // расположение квадратиком
                .padding(top=30.dp)
        )
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
                    .padding(start = 10.dp)
            )
        }
        // grid
        OptionAnswersSection(
            modifier = Modifier.padding(top=15.dp),
            answerOptions = question.answerOptions,
            selectedAnswerOptionId = viewModel.selectedAnswerOptionId,
           // нам надо передать события, что произошло и передать в него выбранный id
            onItemSelected = { optionId -> viewModel.onEvent(QuestionScreenEvent.OptionSelected(optionId)) }
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
                    .padding(bottom = 20.dp)
                    .height(56.dp)
            ){
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue
                    ),
                    contentPadding = PaddingValues(vertical = 15.dp),
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        viewModel.onEvent(QuestionScreenEvent.HintBtnClicked)
                        Toast.makeText(context, "Hint: ${question.hint}", Toast.LENGTH_LONG).show()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.hint_button),
                        tint = Color.Black,
                        contentDescription = "hint_icon_button",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        stringResource(R.string.hint),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 10.dp),
                        color = Color.Black
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BlueGrey
                    ),
                    contentPadding = PaddingValues(vertical = 15.dp),
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        viewModel.onEvent(QuestionScreenEvent.ApplyBtnClicked)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.apply_button),
                        tint = Color.Black,
                        contentDescription = "hint_icon_button",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        stringResource(R.string.apply),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 10.dp),
                        color = Color.Black
                    )
                }
            }
// Finish
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                contentPadding = PaddingValues(vertical = 15.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    viewModel.onEvent(QuestionScreenEvent.FinishBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.finish),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}


@Composable
fun OptionAnswersSection(
    modifier: Modifier = Modifier,
    answerOptions: List<AnswerOption>,
    selectedAnswerOptionId: Int,
    onItemSelected: (Int) -> Unit
){
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.Start,
        contentPadding = PaddingValues(horizontal = 4.dp)

    ) {
        items(answerOptions){currentOptionItem ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f) // Ограничиваем ширину элемента внутри ячейки
                    .padding(horizontal = 4.dp, vertical = 4.dp)
            ){
                AnswerOptionItem(
                    answerOption = currentOptionItem,
                    isSelected = selectedAnswerOptionId == currentOptionItem.id,
                    onClick = {
                        //отработает, когда item будет нажат
                        // передает id нового выбранного item.
                        onItemSelected(currentOptionItem.id)
                    }
                )
            }

        }
    }
}



@Composable
@Preview(showBackground = true)
fun OptionAnswersSectionPreview() {
    // Создаём тестовые данные
    OptionAnswersSection(
        answerOptions = listOf(
            AnswerOption(1, "Ответ 1"),
            AnswerOption(2, "Отт 2"),
            AnswerOption(3, "Ответ 3"),
            AnswerOption(4, "Ответ 4"),
            AnswerOption(5, "Ответ 5"),
            AnswerOption(6, "Ответ 6")
        ),
        selectedAnswerOptionId = 1, // выбран первый вариант
        onItemSelected = {} // пустая лямбда для превью
    )
}


@Composable
@Preview(showBackground = true)
fun QuestionScreenPreview() {
    QuestionScreen(
        question = Question(
            id = 1,
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
        navigate = {}
    )
}


