package com.leoevg.geoquiz.screens.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.theme.Blue
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme

@Composable
fun FinishScreen(navigate: (NavigationPaths) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                painter = painterResource(R.drawable.home_button),
                contentDescription = "home button icon",
                modifier = Modifier
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                stringResource(R.string.quiz_results),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                painter = painterResource(R.drawable.icon_button_gear),
                contentDescription = "home button icon",
                modifier = Modifier
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
// Congratulations
        Text(
            stringResource(R.string.congratulations),
            modifier = Modifier
                .padding(top = 80.dp),
            fontSize = 38.sp,
            fontFamily = FontFamily(Font(R.font.tt_travels_extrabold)),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            stringResource(R.string.your_score_is),
            modifier = Modifier.padding(top = 20.dp),
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.tt_travels_regular)),
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            stringResource(R.string.number_points),
            modifier = Modifier.padding(top = 90.dp),
            fontSize = 140.sp,
            fontFamily = FontFamily(Font(R.font.kharkiv_tone)),
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            stringResource(R.string.points),
            modifier = Modifier.padding(top = 0.dp),
            fontSize = 44.sp,
            fontFamily = FontFamily(Font(R.font.tt_travels_regular)),
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onBackground
        )

// Finish
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.99f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {

                }
            ) {
                Text(
                    stringResource(R.string.go_to_quizzes),
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight.Normal,

                    )
            }
        }



    }

}

@Composable
@Preview(showBackground = true)
fun QuizScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
    FinishScreen {  }
}}

@Composable
@Preview(showBackground = true)
fun QuizScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ) {
        FinishScreen {  }
    }}

