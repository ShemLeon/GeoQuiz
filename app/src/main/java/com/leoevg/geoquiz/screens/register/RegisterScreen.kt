package com.leoevg.geoquiz.screens.register

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
import kotlin.Unit
import androidx.compose.runtime.getValue
import com.leoevg.geoquiz.ui.theme.GeoQuizTheme

@Composable
fun RegisterScreen(
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
){
    val viewModel: RegisterScreenViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // LaunchedEffect - блок, который срабатывает когда переданная в него зависимость изменяется.
    // в данном случае - переход на след экран
    LaunchedEffect(state.isRegisteredIn) {
        if (state.isRegisteredIn) {
            popBackStack() // чистит стак
            navigate(NavigationPaths.Choose)
        }
    }
    LoadingDialog(isLoading = state.isLoading)
    RegisterScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}


@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    state: RegisterScreenState = RegisterScreenState(),
    onEvent: (RegisterScreenEvent) -> Unit
){
    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.create_account),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 90.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Column (
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            state.error?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.nickname,
                onValueChange = {
                    onEvent(RegisterScreenEvent.NicknameChanged(it))
                },
                placeholder = { Text(stringResource(R.string.nickname)) },
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
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = {
                    onEvent(RegisterScreenEvent.EmailChanged(it))
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
                    .fillMaxWidth(),
                value = state.password,
                onValueChange = {
                    onEvent(RegisterScreenEvent.PasswordChanged(it))
                },
                placeholder = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
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
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    onEvent(RegisterScreenEvent.RegisterBtnClicked)
                }
            ) {
                Text(
                    stringResource(R.string.create_account)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenPreview(){
    GeoQuizTheme(
        darkTheme = false
    ) {
        RegisterScreenContent(
            modifier = Modifier,
            state = RegisterScreenState(),
            onEvent = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterScreenDarkPreview(){
    GeoQuizTheme(
        darkTheme = true
    ) {
        RegisterScreenContent(
            modifier = Modifier,
            state = RegisterScreenState(),
            onEvent = {}
        )
    }
}