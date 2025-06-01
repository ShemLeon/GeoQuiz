package com.leoevg.geoquiz.screens.rate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths

import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import androidx.compose.material3.IconButton


import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoevg.geoquiz.ui.components.LoadingDialog

@Composable
fun RateScreen(
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
) {
    val viewModel: RateScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingDialog(isLoading = state.isLoading)
    RateScreenContent(
        navigate = navigate,
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun RateScreenContent(
    modifier: Modifier = Modifier,
    state: RateScreenState = RateScreenState(),
    onEvent: (RateScreenEvent) -> Unit,
    navigate: (NavigationPaths) -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            stringResource(R.string.rate_us),
            modifier = Modifier
                .padding(top = 60.dp),
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
// Stars
        RatingViewStateful(
            maxRating = 5,
            onRatingChanged = { stars ->
                onEvent(RateScreenEvent.StarsChanged(stars))
            }
        )

// Bottom
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

// btn give you content
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.6f)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                //    navigate(NavigationPaths.Userpic)
                }
            ) {
                Box(
                    modifier = Modifier.height(150.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = "camera icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .size(150.dp)
                    )
                    Text(
                        stringResource(R.string.send_content).uppercase(),
                        fontSize = 18.sp,
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
                    navigate(NavigationPaths.Choose)
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
fun RatingViewStateful(
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit = {}
) {
    val rating = remember { mutableStateOf(0) }
    
    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxRating) { index ->
            IconButton(
                onClick = {
                    rating.value = index + 1
                    onRatingChanged(index + 1) // вызов
                }
            ) {
                Icon(
                    imageVector = if (index < rating.value) {
                        Icons.Filled.Star
                    } else {
                        Icons.Filled.StarBorder
                    },
                    contentDescription = "Rate ${index + 1}",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
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
        RateScreenContent(
            modifier = Modifier,
            state = RateScreenState(),
            onEvent = {},
            navigate = {}
        )
    }
}

@Composable
@Preview(showBackground = false)
fun RateScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ) {
        RateScreenContent(
            modifier = Modifier,
            state = RateScreenState(),
            onEvent = {},
            navigate = {}
        )
    }
}
