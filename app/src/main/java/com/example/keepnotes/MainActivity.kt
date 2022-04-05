package com.example.keepnotes

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
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

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private var cancellationSignal: CancellationSignal? = null
    private var authenticated =  mutableStateOf(false)
    private val viewModel: MainViewModel by viewModels {
        SavedStateViewModelFactory(application, this)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(
        ExperimentalPermissionsApi::class, coil.annotation.ExperimentalCoilApi::class,
        kotlinx.coroutines.ExperimentalCoroutinesApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RemoteConfigUtils.init()
        installSplashScreen().apply {
            viewModel.isLoading.value
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KeepNotesTheme {
                val state = viewModel.openBiometricState.collectAsState()
                launchBiometric()
                if (authenticated.value) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        startDestination =
                        if (FirebaseAuth.getInstance().currentUser != null) {
                            if (state.value) {
                                MainScreenDestination
                            } else {
                                WelcomeScreenDestination
                            }
                        } else {
                            WelcomeScreenDestination
                        }
                    )
                } else {
                    Log.d("TAG", "Biometrics failed")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkBiometricSupport(): Boolean {
        val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if (!keyGuardManager.isDeviceSecure) {
            return true
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }

        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
    }

    private val authenticationCallback: android.hardware.biometrics.BiometricPrompt.AuthenticationCallback =
        @RequiresApi(Build.VERSION_CODES.P)
        object : android.hardware.biometrics.BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: android.hardware.biometrics.BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                authenticated.value = true
                Toast.makeText(this@MainActivity, "Authentication Succeeded", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(
                    this@MainActivity,
                    "Authentication Error code: $errorCode",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
            }
        }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun launchBiometric() {
        if (checkBiometricSupport()) {
            val biometricPrompt = android.hardware.biometrics.BiometricPrompt.Builder(this)
                .apply {
                    setTitle(getString(R.string.prompt_info_title))
                    setSubtitle(getString(R.string.prompt_info_subtitle))
                    setConfirmationRequired(false)
                    setNegativeButton(
                        getString(R.string.prompt_info_use_app_password),
                        mainExecutor
                    ) { _, _ ->
                        Toast.makeText(
                            this@MainActivity,
                            "Authentication Cancelled",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.build()

            biometricPrompt.authenticate(
                getCancellationSignal(),
                mainExecutor,
                authenticationCallback
            )
        }
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            Toast.makeText(this, "Authentication Cancelled Signal", Toast.LENGTH_SHORT).show()
        }

        return cancellationSignal as CancellationSignal
    }
}