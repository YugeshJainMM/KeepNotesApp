package com.example.keepnotes.screens

import androidx.compose.material3.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import com.example.domain.models.Response
import com.example.keepnotes.R
import com.example.keepnotes.components.ProgressBar
import com.example.keepnotes.navigation.Screen
import com.example.keepnotes.ui.theme.primaryColor
import com.example.keepnotes.viewmodel.AuthViewModel

@Composable
fun WelcomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val name = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val phone = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val confirmPasswordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(25.dp)
            .verticalScroll(state = rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Welcome",
            style = TextStyle(
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "Please register to continue.",
            style = TextStyle(color = Color.Gray, fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(45.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Name") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(6.dp))
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
            value = phone.value,
            shape = RoundedCornerShape(45.dp),
            onValueChange = { phone.value = it },
            label = { Text(text = "Phone Number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
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
                        tint = if (passwordVisibility.value) primaryColor else Color.Gray,
                        contentDescription = "Password Visibility"
                    )
                }
            },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(45.dp),
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text(text = "Confirm Password") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = {
                    confirmPasswordVisibility.value = !confirmPasswordVisibility.value
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(if (confirmPasswordVisibility.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                        tint = if (confirmPasswordVisibility.value) primaryColor else Color.Gray,
                        contentDescription = "Confirm Password Visibility"
                    )
                }
            },
            visualTransformation = if (confirmPasswordVisibility.value) VisualTransformation.None
            else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.registerUser(
                    email.value.text.trim(),
                    password.value.text.trim(),
                    name.value.text,
                    phone.value.text
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(text = "RegisterUseCase")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already Registered? ")
            TextButton(onClick = {
                navController.navigate(Screen.LoginScreen.route)
            }) {
                Text(text = "Log In")
            }
        }
    }
}




