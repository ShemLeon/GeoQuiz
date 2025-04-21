package com.leoevg.geoquiz.screens.quiz

import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.leoevg.geoquiz.screens.choose.ChooseScreen
import com.leoevg.geoquiz.ui.components.AnswerOptionItem
import com.leoevg.geoquiz.ui.theme.Blue
import com.leoevg.geoquiz.ui.theme.BlueGrey

@Composable
fun QuizScreen(
    navigate: (NavigationPaths) -> Unit
){
    val viewmodel = viewModel<QuizScreenViewModel>()
    var question by remember { mutableStateOf(Question(
        1,
        rightAnswer = 2,
        hint = "There is no hint",
        answerOptions = listOf(AnswerOption(1, "Answer 1"), AnswerOption(2, "Answer 2")),
        imageQuest = "https://media.istockphoto.com/id/641067732/photo/jerusalem-old-city-western-wall-with-israeli-flag.jpg?s=612x612&w=0&k=20&c=GYJ98NjcTV_ROIgqqs3g5OdmLEtprvyCZ2_ZFYMq3hk="
    )) }

    // select by default
    var selectedAnswerOptionId by remember { mutableIntStateOf(-1) }


}




@Composable
@Preview(showBackground = true)
fun QuizScreenPreview(){
    QuizScreen {  }
}