package com.example.keepnotes.utils

import android.util.Log
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigUtils {
    private const val TAG = "RemoteConfigUtils"
    private const val LOGIN_BUTTON_TEXT = "login_text"

    private val DEFAULTS: HashMap<String, Any> =
        hashMapOf(
            LOGIN_BUTTON_TEXT to "Login"
        )

    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init(){
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig{
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                0
            } else {
                60 * 60
            }
        }
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULTS)
            fetchAndActivate().addOnCompleteListener {
                Log.d(TAG, "Remote Config Fetch Complete")
            }
        }
        return remoteConfig
    }

    fun getLoginButtonText(): String = remoteConfig.getString(LOGIN_BUTTON_TEXT)
}
