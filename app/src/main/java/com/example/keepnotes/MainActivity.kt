package com.example.keepnotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.keepnotes.destinations.MainScreenDestination
import com.example.keepnotes.destinations.WelcomeScreenDestination
import com.example.keepnotes.ui.theme.KeepNotesTheme
import com.example.keepnotes.utils.RemoteConfigUtils
import com.example.keepnotes.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.Executor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(application, this)
    }
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RemoteConfigUtils.init()
        installSplashScreen().apply {
            viewModel.isLoading.value
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KeepNotesTheme {
                val context = LocalContext.current as? FragmentActivity
                context?.let { initBioMetric(it) }
                val state = viewModel.openBiometricState.collectAsState()
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startDestination =
                    if (FirebaseAuth.getInstance().currentUser != null) {
                        if (state.value){
                            MainScreenDestination
                        } else {
                            WelcomeScreenDestination
                        }
                    } else {
                        WelcomeScreenDestination
                    }
                )
            }
        }
    }

    private fun initBioMetric(fragmentActivity: FragmentActivity) {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(fragmentActivity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    viewModel.biometricsValue(true)
                    Toast.makeText(
                        applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun getDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }
}
