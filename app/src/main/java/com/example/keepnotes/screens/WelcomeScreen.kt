package com.example.keepnotes.screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import androidx.navigation.NavController
import com.example.keepnotes.R
import com.example.keepnotes.navigation.Screen
import com.example.keepnotes.ui.theme.primaryColor
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun WelcomeScreen(navController: NavController, context: ComponentActivity) {
    val auth = Firebase.auth

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("profile")

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
        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = {
                val profileUpdates = userProfileChangeRequest {
                    displayName = "User"
                }
                coroutineScope.launch {
                    val result = auth.currentUser?.updateProfile(profileUpdates)?.await()
                }
                auth.createUserWithEmailAndPassword(
                    email.value.text.trim(),
                    password.value.text.trim(),
                )
                    .addOnSuccessListener {
                        Log.d("TAG", "WelcomeScreen: Success")
                    }
                    .addOnCompleteListener(context) { task ->
                        if (task.isSuccessful) {
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference.child((currentUser?.uid!!))
                            currentUserDb.child("name").setValue(name.value.text)
                            currentUserDb.child("phone_number").setValue(phone.value.text)
                            Log.d("TAG", "WelcomeScreen: Registration Success")
                            navController.navigate(Screen.MainScreen.route)
                        } else (Log.d("TAG", "Failed: ${task.exception}"))
                    }

            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(text = "Register")
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




