package com.leoevg.geoquiz.screens.choose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)
        .background(MaterialTheme.colorScheme.background),
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
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
// Прокрутка
            LazyRow(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(MaterialTheme.colorScheme.background),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(typeGames){
                    GameModelItem(
                        modifier = Modifier,
                        typeGame = it,
                        isSelected = selectedTypeGame.typeGameId==it.typeGameId
                    ) {
                        selectedTypeGame = it
                    }
                }
            }

            Text(
                text = stringResource(selectedTypeGame.typeGameDescResId),
                fontSize = 20.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground
            )
            Image(
                painter = painterResource(selectedTypeGame.typeGameImg),
                contentDescription = "item Desc",
                modifier = Modifier
                    .padding(top = 50.dp)
            )
        }

        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ){
// btn Start the quiz
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onQuizSelected(NavigationPaths.Quiz(selectedTypeGame))
                }
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
                    modifier = Modifier
                        .padding(start = 30.dp),
                    color = MaterialTheme.colorScheme.background
                )
            }
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