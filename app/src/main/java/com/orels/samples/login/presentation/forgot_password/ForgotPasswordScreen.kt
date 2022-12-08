package com.orels.samples.login.presentation.forgot_password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orels.components.Loading
import com.orels.samples.R
import com.orels.samples.login.presentation.components.AuthenticationInput

@Composable
fun ForgotPasswordScreen(viewModel: ForgotPasswordViewModel = hiltViewModel()) {
    val state = viewModel.state

    var username by rememberSaveable { mutableStateOf("") }

    var code by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    when (state.state) {
        State.ForgotPassword -> ForgotPasswordContent(isLoading = false,
            username = username,
            onUsernameChange = { username = it },
            onForgotPassword = viewModel::onForgotPassword)
        State.LoadingForgotPassword -> ForgotPasswordContent(isLoading = true, username = username)
        State.ResetPassword -> ResetPasswordContent(
            code = code,
            password = password,
            confirmPassword = confirmPassword,
            onCodeChange = { code = it },
            onPasswordChange = { password = it },
            onConfirmPasswordChange = { confirmPassword = it },
            isLoading = false,
            onResetPassword = viewModel::onResetPassword)
        State.LoadingResetPassword -> ResetPasswordContent(isLoading = true,
            code = code,
            password = password,
            confirmPassword = confirmPassword)
        State.Done -> Text("Done")
        State.Error -> TODO()
    }
}

@Composable
fun ForgotPasswordContent(
    isLoading: Boolean,
    username: String,
    onUsernameChange: ((String) -> Unit)? = null,
    onForgotPassword: ((String) -> Unit)? = null,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            text = stringResource(R.string.forgot_the_password),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.forgot_password_message),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Thin,
        )

        AuthenticationInput(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            placeholder = stringResource(R.string.username),
            value = username,
            paddingLeadingIconEnd = 10.dp,
            paddingTrailingIconStart = 10.dp,
            onValueChange = { onUsernameChange?.invoke(it) },
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
            onClick = { onForgotPassword?.invoke(username) },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            if (isLoading) {
                Loading(size = 16.dp, color = MaterialTheme.colorScheme.background)
            } else {
                Text(
                    text = stringResource(R.string.reset_password),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
fun ResetPasswordContent(
    code: String,
    password: String,
    confirmPassword: String,
    onCodeChange: ((String) -> Unit)? = null,
    onPasswordChange: ((String) -> Unit)? = null,
    onConfirmPasswordChange: ((String) -> Unit)? = null,
    isLoading: Boolean,
    onResetPassword: ((code: String, password: String, confirmPassword: String) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            text = stringResource(R.string.change_the_password),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Insert the code sent to your email and create a new password",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Thin,
        )

        AuthenticationInput(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            placeholder = stringResource(R.string.code),
            value = code,
            paddingLeadingIconEnd = 10.dp,
            paddingTrailingIconStart = 10.dp,
            onValueChange = { onCodeChange?.invoke(it) }
        )

        AuthenticationInput(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            placeholder = stringResource(R.string.password),
            value = password,
            paddingLeadingIconEnd = 10.dp,
            paddingTrailingIconStart = 10.dp,
            onValueChange = { onPasswordChange?.invoke(it) }
        )
        AuthenticationInput(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp),
            placeholder = stringResource(R.string.confirm_password),
            value = confirmPassword,
            paddingLeadingIconEnd = 10.dp,
            paddingTrailingIconStart = 10.dp,
            onValueChange = { onConfirmPasswordChange?.invoke(it) }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp),
            onClick = { onResetPassword?.invoke(code, password, confirmPassword) },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                contentColor = MaterialTheme.colorScheme.background,
            ),
        ) {
            if (isLoading) {
                Loading(size = 16.dp, color = MaterialTheme.colorScheme.background)
            } else {
                Text(
                    text = stringResource(R.string.reset_password),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}
//        Input(
//            modifier = Modifier
//                .fillMaxWidth()
//                .drawBehind {
//                    drawRect(
//                        color = Color.Transparent,
//                        size = size,
//                    )
//                }
//                .padding(horizontal = 16.dp, vertical = 12.dp),
//            placeholder = stringResource(R.string.password),
//            paddingLeadingIconEnd = 10.dp,
//            paddingTrailingIconStart = 10.dp,
//            onValueChange = {}
//        )
//        Text(
//            modifier = Modifier
//                .padding(horizontal = 20.dp)
//                .noRippleClickable { },
//            text = stringResource(R.string.forgot_password),
//            style = MaterialTheme.typography.bodySmall,
//            fontWeight = FontWeight.SemiBold,
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = stringResource(R.string.dont_have_an_account),
//                style = MaterialTheme.typography.bodySmall,
//                fontWeight = FontWeight.Light,
//            )
//            Text(
//                modifier = Modifier
//                    .padding(horizontal = 8.dp)
//                    .noRippleClickable { },
//                text = stringResource(R.string.sign_up),
//                style = MaterialTheme.typography.bodySmall,
//                fontWeight = FontWeight.Bold,
//            )
//        }