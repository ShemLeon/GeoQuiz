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
import com.leoevg.geoquiz.R
import com.leoevg.geoquiz.navigation.NavigationPaths
import com.leoevg.geoquiz.ui.components.LoadingDialog
import com.leoevg.geoquiz.ui.theme.Bg
import com.leoevg.geoquiz.ui.theme.Blue
import com.leoevg.geoquiz.ui.theme.BlueGrey

@Composable
fun RegisterScreen(
    navigate: (NavigationPaths) -> Unit,
    popBackStack: () -> Unit
){
    val viewModel: RegisterScreenViewModel = hiltViewModel()
    // LaunchedEffect - блок, который срабатывает когда переданная в него зависимость изменяется.
    // в данном случае - переход на след экран
    LaunchedEffect(viewModel.isRegisteredIn) {
        if (viewModel.isRegisteredIn) {
            popBackStack() // чистит стак, чтобы при нажатии
            // кнопки назад не сохранило введенный логин/пароль
            navigate(NavigationPaths.Choose)
        }
    }

    LoadingDialog(isLoading = viewModel.isLoading)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Bg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.create_account),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 90.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            viewModel.error?.let { error ->
                Text(
                    text = error,
                    color = Color.Red
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.nickname,
                onValueChange = {
                    viewModel.onEvent(RegisterScreenEvent.NicknameChanged(it))
                                },
                placeholder = { Text(stringResource(R.string.nickname)) },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledContainerColor = BlueGrey,
                    focusedContainerColor = BlueGrey,
                    errorContainerColor = BlueGrey,
                    unfocusedContainerColor = BlueGrey
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email,
                onValueChange = {
                    viewModel.onEvent(RegisterScreenEvent.EmailChanged(it))
                                },
                placeholder = { Text(stringResource(R.string.email)) },
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledContainerColor = BlueGrey,
                    focusedContainerColor = BlueGrey,
                    errorContainerColor = BlueGrey,
                    unfocusedContainerColor = BlueGrey
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.password,
                onValueChange = {
                    viewModel.onEvent(RegisterScreenEvent.PasswordChanged(it))
                                },
                placeholder = { Text(stringResource(R.string.password)) },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledContainerColor = BlueGrey,
                    focusedContainerColor = BlueGrey,
                    errorContainerColor = BlueGrey,
                    unfocusedContainerColor = BlueGrey
                )
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    viewModel.onEvent(RegisterScreenEvent.RegisterBtnClicked)
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
//    RegisterScreen {  }
}