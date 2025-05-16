package com.leoevg.geoquiz.screens.finish

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.outlined.Logout
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme

@Composable
fun RateScreen(navigate: (NavigationPaths) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Text(
                stringResource(R.string.quiz_results),
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Logout,
                contentDescription = "Logout btn",
                modifier = Modifier.clickable {
                    FirebaseAuth.getInstance().signOut()
//TODO: не забыть настроить- popBackStack()
                    navigate(NavigationPaths.Login)
                },
                tint = MaterialTheme.colorScheme.onBackground
            )


        }
        Text(
            stringResource(R.string.rate_us),
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
                onClick = {

                }
            ) {
                Text(
                    stringResource(R.string.go_to_quizzes),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Normal,

                    )
            }
        }



    }

}

@Composable
@Preview(showBackground = true)
fun RateScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
        RateScreen {  }
    }
}

@Composable
@Preview(showBackground = false)
fun RateScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ) {
        RateScreen {  }
    }
}