package com.example.keepnotes.screens

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

@Composable
@com.ramcosta.composedestinations.annotation.Destination
fun BiometricScreen(context: FragmentActivity) {
//    val fragmentContext = LocalContext.current as FragmentActivity
    initBioMetric(context)
}

private lateinit var executor: Executor
private lateinit var biometricPrompt: BiometricPrompt
private lateinit var promptInfo: BiometricPrompt.PromptInfo

private fun initBioMetric(fragmentActivity: FragmentActivity) {
    executor = ContextCompat.getMainExecutor(fragmentActivity)
    biometricPrompt = BiometricPrompt(fragmentActivity, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                Log.d("TAG", "Authentication Error ")
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                super.onAuthenticationSucceeded(result)
                Log.d("TAG", "Authentication Success ")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Log.d("TAG", "Authentication Failed ")
            }
        })

    promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric login for my app")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Use account password")
        .build()

    biometricPrompt.authenticate(promptInfo)
}