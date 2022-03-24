package com.example.keepnotes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.auth.AuthUseCases
import com.example.domain.models.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {

    var register: Any by mutableStateOf(Response.Success(null))
        private set

    var signin: Any by mutableStateOf(Response.Success(null))
        private set

    fun registerUser(email: String, password: String, name: String, phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCases.register(email, password, name, phone).collect{
                register = it
            }
        }
    }

    fun signInUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authUseCases.signIn(email, password).collect{
               signin = it
            }
        }
    }
}