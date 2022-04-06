package com.example.keepnotes.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.auth.AuthUseCases
import com.example.domain.models.Response
import com.example.keepnotes.utils.RemoteConfigUtils
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {
    var register = MutableSharedFlow<Response<Any>>()
        private set

    var signin = MutableSharedFlow<Response<Any>>()
        private set

    fun registerUser(email: String, password: String, name: String, phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCases.register(email, password, name, phone).collect{
                register.emit(Response.Success(it))
            }
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCases.signIn(email, password).collect{
               signin.emit(Response.Success(it))
            }
        }
    }

//    fun fetch(){
//        remoteConfig.fetchAndActivate().addOnCompleteListener{
//            Log.d("TAG", remoteConfig.getString(RemoteConfigUtils.LOGIN_BUTTON_TEXT))
//        }
//    }
    fun getLoginButtonText(): String = Firebase.remoteConfig.getString(RemoteConfigUtils.LOGIN_BUTTON_TEXT)
//    fun getBiometricsBoolean(): Boolean = remoteConfig.getBoolean(RemoteConfigUtils.BIOMETRICS_ON_OFF)
}