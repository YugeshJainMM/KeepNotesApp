package com.example.keepnotes.screens

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.Response
import com.example.keepnotes.R
import com.example.keepnotes.destinations.ForgotPasswordScreenDestination
import com.example.keepnotes.destinations.MainScreenDestination
import com.example.keepnotes.viewmodel.AuthViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LoginScreen(navigator: DestinationsNavigator, authViewModel: AuthViewModel = hiltViewModel()) {

    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        authViewModel.signin.collect{
            when (it) {
                is Response.Success ->
                    navigator.navigate(MainScreenDestination)
                is Response.Error -> Log.d("TAG", it.message)
                else -> {
                    Log.d("TAG", "LOGIN ELSE BLOCK")
                }
            }
        }
    }

    Column(
        modifier = Modifier.padding(25.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Welcome Back!",
            style = TextStyle(
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Please Login to continue.",
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(36.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(45.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(45.dp),
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(if (passwordVisibility.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                        tint = Color.Gray,
                        contentDescription = "as"
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        val bundle = Bundle()
        Button(
            onClick = {
                authViewModel.signInUser(
                    email.value.text.trim(),
                    password.value.text.trim()
                )
//                bundle.putString(FirebaseAnalytics.Param.METHOD, method)
//                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(text = "Login")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = {
                navigator.navigate(ForgotPasswordScreenDestination)
            }) {
                Text(text = "Forgot Password?")
            }
        }
    }
}