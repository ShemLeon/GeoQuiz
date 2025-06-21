package com.leoevg.geoquiz.screens.admin

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import kotlinx.coroutines.launch
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.leoevg.geoquiz.ui.components.ChooseGameModeDialog
import com.leoevg.geoquiz.ui.components.LoadingDialog
import kotlin.String
import com.leoevg.geoquiz.ui.theme.DarkGreen
import com.leoevg.geoquiz.ui.theme.DarkRed


@Composable
fun AdminScreen(
    navigate: (NavigationPaths) -> Unit
){
    val viewModel: AdminScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LoadingDialog(isLoading = state.isLoading)

    if (state.isChooseGameModeDialogRequested) {
        ChooseGameModeDialog(
            onModesSelected = {
                val selectedModesNames = it.map { currentMode ->
                    context.getString(currentMode.typeGameNameResId)
                }
                viewModel.onEvent(AdminScreenEvent.ChooseGameModeDialogModesSelected(selectedModesNames))
            }
        )
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        AdminScreenContent(
            modifier = Modifier.padding(it),
            navigate = navigate,
            state = state,
            onEvent = viewModel::onEvent,
            showSnackBar = { textLabel: String, actionLabel: String ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = textLabel,
                        actionLabel = actionLabel
                    )
                }
            }
        )
    }

}



@Composable
fun AdminScreenContent(
    modifier: Modifier = Modifier,
    state: AdminScreenState = AdminScreenState(),
    onEvent: (AdminScreenEvent) -> Unit,
    navigate: (NavigationPaths) -> Unit,
    showSnackBar: (String, String) -> Unit
) {
    var isImageZoomed by remember { mutableStateOf(false) }
    // анимация разворачивания по времени
    val transition = updateTransition(targetState = isImageZoomed, label = "ZoomTransition")
    val imageScale by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500) },
        label = "ImageScale"
    ){ zoomed ->
        if (zoomed) 1f else 0f
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AdminScreen"
        )
        Row() {
            // Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // расположение квадратиком
                    .padding(top=30.dp)
            ) {
                AsyncImage(
                    model = state.currentSuggestion?.imageUrl?:"",
                    contentDescription = "Suggested image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .pointerInput(Unit) { // мод для "тапания пальцами"
                            detectTapGestures( // слушатель для тапов
                                onDoubleTap = { // исполнение после дабл тапа
                                    isImageZoomed = true
                                }
                            )
                        }
                )
            }
        }
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Country: ${state.currentSuggestion?.country?:"null Country"}",
            fontSize = 20.sp
            )

        Spacer(modifier = Modifier.weight(1f)) // Выталкивает всё, что ниже, к низу
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
                .height(90.dp)
        ) {
// Approve
            Button(
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.9f)
                    .fillMaxWidth(fraction = 0.6f),
                //              .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkGreen
                ),
                contentPadding = PaddingValues(vertical = 15.dp),
                shape = RoundedCornerShape(25.dp),
                onClick = {
                    showSnackBar("Fields cannot be empty", "Close")
                    onEvent(AdminScreenEvent.ApproveBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.approve),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
// Reject
            Button(
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.9f)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkRed
                ),
                contentPadding = PaddingValues(vertical = 10.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    showSnackBar("Fields cannot be empty", "Close")
                    onEvent(AdminScreenEvent.RejectSuggestionClicked)
                }
            ) {
                Text(
                    stringResource(R.string.reject),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }


    if (isImageZoomed) {
        Box(
            modifier= Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha=0.9f))
                .clickable{ isImageZoomed = false}
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ){
            AsyncImage(
                model = "",
                contentDescription = "Question image",
                modifier = Modifier
                    .fillMaxWidth() // Пропорционально
                    .graphicsLayer {
                        scaleX = imageScale
                        scaleY = imageScale
                    }
                    .padding(20.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .pointerInput(Unit){ // мод для "тапания пальцами"
                        detectTapGestures( // слушатель для тапов
                            onTap = { // исполнение после одиночного тапа
                                isImageZoomed = false // Сворачиваем изображение
                            },
                            onDoubleTap = { // исполнение после дабл тапа
                                isImageZoomed = false // Сворачиваем изображение
                            }
                        )
                    },
                contentScale = ContentScale.Fit // Вместо обрезки - подгон под размер
            )
        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = 1)
fun AdminScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
        AdminScreenContent(
            modifier = Modifier,
            state = AdminScreenState(),
            onEvent = {},
            navigate = {},
            showSnackBar = { textLabel: String, actionLabel: String -> }
        )
    }
}