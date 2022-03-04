package com.example.keepnotes.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object RegisterationScreen : Screen("registration_screen")
    object MainScreen : Screen("main_screen")
    object ForgotPasswordScreen : Screen("forgot_password_screen")

}