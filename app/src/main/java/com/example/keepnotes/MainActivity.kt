package com.example.keepnotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.keepnotes.navigation.SetupNavGraph
import com.example.keepnotes.ui.theme.KeepNotesTheme
import com.example.keepnotes.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            viewModel.isLoading.value
        }
        setContent {
            KeepNotesTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
//                WelcomeScreen()
            }
        }
    }
}