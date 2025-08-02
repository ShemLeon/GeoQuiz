package com.leoevg.geoquiz.screens.choose

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.typeGames
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.GameModelItem
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle

import kotlin.Unit

@Composable
fun ChooseScreen(
    navigate: (NavigationPaths) -> Unit,
    onQuizSelected: (NavigationPaths.Quiz) -> Unit,
    popBackStack: () -> Unit
) {
    val viewModel: ChooseScreenViewModel = hiltViewModel()
    val isUserAnAdmin by viewModel.isAdmin.collectAsStateWithLifecycle()

    ChooseScreenContent(
        isUserAnAdmin = isUserAnAdmin,
        navigate = navigate,
        onQuizSelected = onQuizSelected,
        popBackStack = popBackStack
    )
}

@Composable
fun ChooseScreenContent(
    isUserAnAdmin: Boolean = false,
    navigate: (NavigationPaths) -> Unit = {},
    onQuizSelected: (NavigationPaths.Quiz) -> Unit = {},
    popBackStack: () -> Unit = {}
){
    var selectedTypeGame by remember { mutableStateOf(typeGames[0]) }
    val context = LocalContext.current
    val hapticFeedback = LocalHapticFeedback.current
    // Get current config for screen size access
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp // Screen height in dp
    val buttonHeight = screenHeight * 0.1f

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)
        .background(MaterialTheme.colorScheme.background),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = buttonHeight + 25.dp), // отступ снизу для кнопки
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Panel Top Text
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.choose_game_mode),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isUserAnAdmin) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Admin btn",
                                tint = Color.Red,
                                modifier = Modifier.clickable {
                                    navigate(NavigationPaths.Admin)
                                }
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = "Logout btn",
                            modifier = Modifier.padding(start = 10.dp).clickable {
                                FirebaseAuth.getInstance().signOut()
                                popBackStack()
                                navigate(NavigationPaths.Login)
                            },
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                // Lazy Row
                LazyRow(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(typeGames) {0
                        GameModelItem(
                            modifier = Modifier,
                            typeGame = it,
                            isSelected = selectedTypeGame.typeGameId == it.typeGameId
                        ) {
                            selectedTypeGame = it
                        }
                    }
                }

// Text Description
                BasicText(
                    text = stringResource(selectedTypeGame.typeGameDescResId),
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .padding(horizontal = 5.dp)
                        .fillMaxWidth()
                        .height(screenHeight * 0.3f),
                    maxLines = 8,
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 18.sp,
                        maxFontSize = 30.sp
                    ),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                        textAlign = TextAlign.Start
                    )
                )

// Image
                Image(
                    painter = painterResource(selectedTypeGame.typeGameImg),
                    contentDescription = "item Desc",
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .padding(bottom = 5.dp)
                        .fillMaxWidth()
                        .weight(1f)
                )
            }

            // Карта с кнопкой привязана к низу экрана
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
                    .padding(bottom = 25.dp)
                    .padding(horizontal = 10.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Button(
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(25.dp),
                    onClick = {
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        onQuizSelected(NavigationPaths.Quiz(selectedTypeGame))
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_button_play),
                            contentDescription = "play_icon_button",
                            tint = MaterialTheme.colorScheme.background
                        )
                        Text(
                            stringResource(R.string.start_the_quiz),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(start = 25.dp),
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }


        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ChooseScreenPreview() {
    GeoQuizTheme(
        darkTheme = false
    ) {
        ChooseScreenContent(
            navigate = {},
            onQuizSelected = {},
            popBackStack = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChooseScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ){
        ChooseScreenContent(
            navigate = {},
            onQuizSelected = {},
            popBackStack = {}
        )
    }
}