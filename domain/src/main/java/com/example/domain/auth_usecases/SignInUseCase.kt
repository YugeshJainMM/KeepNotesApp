package com.example.domain.auth_usecases

import com.example.domain.repository.AuthRepository

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.signIn(email, password)
}