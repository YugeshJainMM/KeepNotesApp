package com.example.keepnotes.utils

object RemoteConfigUtils {
    const val TAG = "RemoteConfigUtils"
    const val LOGIN_BUTTON_TEXT = "LOGIN_BUTTON_TEXT"
    const val BIOMETRICS_ON_OFF = "BIOMETRICS_ON_OFF"

    val DEFAULTS: HashMap<String, Any> =
        hashMapOf(
            LOGIN_BUTTON_TEXT to "Login",
            BIOMETRICS_ON_OFF to true
        )
}
