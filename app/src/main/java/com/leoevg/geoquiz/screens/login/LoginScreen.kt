package com.leoevg.geoquiz.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.LoadingDialog
import com.leoevg.geoquiz.ui.theme.Bg
import com.leoevg.geoquiz.ui.theme.Blue
import com.leoevg.geoquiz.ui.theme.BlueGrey
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme

import kotlin.Unit
import androidx.compose.runtime.getValue

@Composable
fun LoginScreen(
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
){
    val viewModel: LoginScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // LaunchedEffect - блок, который срабатывает когда переданная в него зависимость изменяется.
    // в данном случае - переход на след экран
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            popBackStack() // чистит стак, чтобы при нажатии
            // кнопки назад не сохранило введенный логин/пароль
            navigate(NavigationPaths.Choose)
        }
    }
    LoadingDialog(isLoading = state.isLoading)
    LoginScreenContent(
        navigate = navigate,
        state = state,
        onEvent = viewModel::onEvent
        // onEvent должен принять в себя лямда блок, который в принципе, тоже есть функция.
        // :: - ссылка на функцию
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit,
    navigate: (NavigationPaths) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.app_login_title),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 90.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Column (
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            state.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                value = state.email,
                onValueChange = {
                    // it - новое значение введенное юзером, евентом передаем его в viewModel
                    onEvent(LoginScreenEvent.EmailChanged(it))
                },
                placeholder = { Text(stringResource(R.string.email)) },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                    errorContainerColor = MaterialTheme.colorScheme.secondary,
                  // unfocusedContainerColor = BlueGrey
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = state.password,
                onValueChange = {
                    onEvent(LoginScreenEvent.PasswordChanged(it))
                },
                placeholder = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                    errorContainerColor = MaterialTheme.colorScheme.secondary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
// btn Login
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    onEvent(LoginScreenEvent.LoginBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.login)
                )
            }
// btn Create account
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    navigate(NavigationPaths.Register)
                }
            ) {
                Text(
                    stringResource(R.string.create_account),
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
        LoginScreenContent(
            modifier = Modifier,
            state = LoginScreenState(),
            onEvent = {},
            navigate = {}
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = 1)
fun LoginScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ) {
        LoginScreenContent(
            modifier = Modifier,
            state = LoginScreenState(),
            onEvent = {},
            navigate = {}
        )
    }
}