package com.leoevg.geoquiz.screens.choose

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.data.model.typeGames
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.GameModelItem
import com.leoevg.geoquiz.ui.theme.Blue

@Composable
fun ChooseScreen(
    navigate: (NavigationPaths) -> Unit = {},
    popBackStack: () -> Unit = {}
){
    var selectedTypeGame by remember { mutableStateOf(typeGames[0]) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Row (
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(R.string.choose_game_mode),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = "Logout btn",
                    modifier = Modifier.clickable {
                        FirebaseAuth.getInstance().signOut()
                        popBackStack()
                        navigate(NavigationPaths.Login)
                    },
                    tint = Color.Black
                )
            }

            LazyRow(
                modifier = Modifier.
                padding(top = 30.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ){
                items(typeGames){
                    GameModelItem(
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
                    .padding(top = 50.dp)
                    .padding(horizontal = 5.dp)
                    .fillMaxWidth()
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
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 40.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.play_button),
                    contentDescription = "play_icon_button",

                    )
                Text(
                    stringResource(R.string.start_the_quiz),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(start = 30.dp)
                )
            }
        }
}
}


@Composable
@Preview(showBackground = true)
fun ChooseScreenPreview(){
    ChooseScreen {  }
}