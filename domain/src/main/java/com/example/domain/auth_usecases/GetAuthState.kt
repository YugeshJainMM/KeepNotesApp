package com.example.domain.auth_usecases

import com.example.domain.repository.AuthRepository

class GetAuthState(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getFirebaseAuthState()
}