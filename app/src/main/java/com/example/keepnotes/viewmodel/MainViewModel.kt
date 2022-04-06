package com.example.keepnotes.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.auth.AuthUseCases
import com.example.keepnotes.utils.RemoteConfigUtils
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {

    init {
        fetch()
    }
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    var openBiometricState = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            delay(3000)
            _isLoading.value = false
        }
    }

    fun biometricsValue(isSuccess: Boolean){
        openBiometricState.value = isSuccess
    }

    fun fetch(){
        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener{
            Log.d("TAG", Firebase.remoteConfig.getString(RemoteConfigUtils.LOGIN_BUTTON_TEXT))
        }
    }
//    fun getLoginButtonText(): String = remoteConfig.getString(RemoteConfigUtils.LOGIN_BUTTON_TEXT)
    fun getBiometricsBoolean(): Boolean = Firebase.remoteConfig.getBoolean(RemoteConfigUtils.BIOMETRICS_ON_OFF)

}