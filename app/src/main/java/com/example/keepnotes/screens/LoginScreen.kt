package com.example.keepnotes.screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavController
import com.example.keepnotes.R
import com.example.keepnotes.navigation.Screen
import com.example.keepnotes.ui.theme.primaryColor
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(navController: NavController, context: ComponentActivity) {
    val auth = Firebase.auth

    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val PasswordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(25.dp)
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_login),
//            contentDescription = "Login Image",
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(20.dp)
//        )
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
                    PasswordVisibility.value = !PasswordVisibility.value
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(if (PasswordVisibility.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                        tint = if (PasswordVisibility.value) primaryColor else Color.Gray,
                        contentDescription = "as"
                    )
                }
            },
            visualTransformation = if (PasswordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                auth.signInWithEmailAndPassword(
                    email.value.text.trim(),
                    password.value.text.trim()
                )
                    .addOnCompleteListener(context) { task ->
                        if (task.isSuccessful) {
                            Log.d("TAG", "WelcomeScreen: Login Success")
                            navController.navigate(Screen.MainScreen.route)
                        } else (Log.d("TAG", "Failed: ${task.exception}")
                                )
                    }
            },
//            enabled = isFormValid,
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
                navController.popBackStack()
                navController.navigate(Screen.ForgotPasswordScreen.route)
            }) {
                Text(text = "Forgot Password?")
            }
        }
    }
}

