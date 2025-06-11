package com.leoevg.geoquiz.screens.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog

@Composable
fun FinishScreen(
    finalScore: Int,
    navigate: (NavigationPaths) -> Unit = {},
    viewModel: FinishScreenViewModel = hiltViewModel<FinishScreenViewModel, FinishScreenViewModel.FinishScreenViewModelFactory>{
        factory ->
        factory.create(finalScore)
    }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var showAnimation by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("animation/AnimationFinish.json"))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = 1,
        speed = 1f,
        restartOnPlay = false
    )
    LaunchedEffect(progress) {
        if (progress == 1f) {
            showAnimation = false
        }
    }
    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.fillMaxSize()
    )



    LaunchedEffect(Unit) {
        viewModel.proceedMaxScore()
    }
    FinishScreenContent(
        modifier = Modifier,
        state = state,
        navigate = navigate
    )
}

@Composable
fun FinishScreenContent(
    modifier: Modifier = Modifier,
    state: FinishScreenState,
    navigate: (NavigationPaths) -> Unit = {}
){
    // Загружаем композицию анимации из assets
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("your_animation.json")) // Замените "your_animation.json" на имя вашего файла
    // Контролируем прогресс анимации
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever // или 1, если нужно проиграть один раз
        // restartOnPlay = false // можно добавить, если не нужно перезапускать при рекомпозиции, когда shouldPlay = true
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
// title
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            repeat(3) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "home button icon",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                stringResource(R.string.quiz_results),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
            repeat(3) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "home button icon",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
// Congratulations
        Text(
            stringResource(R.string.congratulations),
            modifier = Modifier
                .padding(top = 40.dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            stringResource(R.string.your_score_is),
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = state.finalScore.toString(),
            modifier = Modifier.padding(top = 70.dp),
            fontSize = 160.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            stringResource(R.string.points),
            modifier = Modifier
                .offset(y=(-20).dp),
            fontSize = 44.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
// MaxScore
        Text(
            text = stringResource(R.string.max_score)
                    + (state.maxScore?.let { state.maxScore  } ?: "..."),
            modifier = Modifier,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )


// Bottom
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

// btn help_project
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.6f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    navigate(NavigationPaths.Rate)
                }
            ) {
                Box(
                    modifier = Modifier.height(100.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_help_project),
                        contentDescription = "help_project_icon_button",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(100.dp)
                    )
                    Text(
                        stringResource(R.string.help_project).uppercase(),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }


            }

// btn back
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = { navigate(NavigationPaths.Choose) }
            ) {
                Text(
                    stringResource(R.string.go_to_quizzes),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true, name = "Finish Screen Light")
fun FinishScreenLightPreview() {
    GeoQuizTheme(
        darkTheme = false
    ) {
        FinishScreenContent(state = FinishScreenState())
    }
}

@Composable
@Preview(showBackground = false, name = "Finish Screen Dark")
fun FinishScreenDarkPreview() {
    GeoQuizTheme(
        darkTheme = true
    ) {
        FinishScreenContent(state = FinishScreenState())
    }
}