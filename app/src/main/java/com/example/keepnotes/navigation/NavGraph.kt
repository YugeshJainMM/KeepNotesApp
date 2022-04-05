package com.example.keepnotes.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.keepnotes.screens.ForgotPasswordScreen
import com.example.keepnotes.screens.LoginScreen
import com.example.keepnotes.screens.MainScreen
import com.example.keepnotes.screens.WelcomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.RegistrationScreen.route
    ) {
//        composable(route = Screen.RegistrationScreen.route){
//            WelcomeScreen(navController = navController)
//        }
//        composable(route = Screen.LoginScreen.route) {
//            LoginScreen(navController = navController)
//        }
//        composable(route = Screen.MainScreen.route) {
//            MainScreen()
//        }
        composable(route = Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen()
        }
    }
}