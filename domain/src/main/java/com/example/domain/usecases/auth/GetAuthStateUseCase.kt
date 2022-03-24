package com.example.domain.usecases.auth

import com.example.domain.repository.AuthRepository

class GetAuthStateUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}