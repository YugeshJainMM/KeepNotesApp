package com.example.keepnotes.screens

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.keepnotes.destinations.MainScreenDestination
import java.util.concurrent.Executor


@Composable
fun BiometricPopup() {
    val context = LocalContext.current
    lateinit var executor: Executor
    lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo

    Scaffold {
        Text(text = "hi")
    }

    fun initBioMetric(fragmentActivity: FragmentActivity) {
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = androidx.biometric.BiometricPrompt(fragmentActivity, executor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: androidx.biometric.BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    MainScreenDestination
                    Toast.makeText(
                        context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        context, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

}
