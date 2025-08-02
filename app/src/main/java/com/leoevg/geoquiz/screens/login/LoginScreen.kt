package com.leoevg.geoquiz.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme
import kotlin.Unit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import kotlinx.coroutines.launch
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback


@Composable
fun LoginScreen(
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
){
    val viewModel: LoginScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            popBackStack() // чистит стак, чтобы при нажатии
            // кнопки назад не сохранило введенный логин/пароль
            navigate(NavigationPaths.Choose)
        }
    }
    LaunchedEffect(state.snackbarMessage) {
        state.snackbarMessage?.let { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "Close"
                )
            viewModel.clearSnackbarMessage()
        }
    }

    LoadingDialog(isLoading = state.isLoading)

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        LoginScreenContent(
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
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit,
    navigate: (NavigationPaths) -> Unit,
    showSnackBar: (String, String) -> Unit
){
    // Get current config for screen size access
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp // Screen height in dp
    val buttonHeight = screenHeight * 0.045f
    val hapticFeedback = LocalHapticFeedback.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.app_login_title),
            fontSize = 35.sp,
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
            Card(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .height(buttonHeight),
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxSize(), // Заполняем всю карточку
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent // Делаем фон кнопки прозрачным, так как цвет задан в Card
                    ),
                    shape = RoundedCornerShape(25.dp), // Можно оставить или убрать, если форма Card уже подходит
                    onClick = {
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                        onEvent(LoginScreenEvent.LoginBtnClicked)
                    }
                ) {
                    BasicText(
                        stringResource(R.string.login),
                        modifier = Modifier
                            .fillMaxWidth(),
                        maxLines = 1,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 10.sp,
                            maxFontSize = 28.sp
                        ),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.background,
                            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
// btn Create account
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .height(buttonHeight),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),

                shape = RoundedCornerShape(25.dp),
                onClick = {
                    navigate(NavigationPaths.Register)
                }
            ) {
                BasicText(
                    stringResource(R.string.create_account),
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 10.sp,
                        maxFontSize = 38.sp
                    ),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                ))
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
            navigate = {},
            showSnackBar = { textLabel: String, actionLabel: String -> }
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
            navigate = {},
            showSnackBar = { textLabel: String, actionLabel: String -> }
        )
    }
}