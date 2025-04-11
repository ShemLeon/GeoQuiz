package com.leoevg.geoquiz.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.screens.choose.ChooseScreen

@Composable
fun QuizScreen(navigate: (NavigationPaths) -> Unit){
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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.play_button),
                    contentDescription = "play_icon_button",

                )
                Icon(
                    painter = painterResource(R.drawable.play_button),
                    contentDescription = "play_icon_button",
                )
            }


        }
        Image(
            painter = painterResource(R.drawable.card_easy),
            contentDescription = "item Desc",
            modifier = Modifier
                .padding(top = 50.dp)
        )
        Row (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
            text = stringResource(R.string.correct_answer),
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 10.dp)

        ) }

    }
}

@Composable
fun OptionsAnswerSection(modifier: Modifier){
    Column (
        modifier = modifier
            .fillMaxWidth()
    ){
        Text("option1")
    }
}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview(){
    QuizScreen {  }
}