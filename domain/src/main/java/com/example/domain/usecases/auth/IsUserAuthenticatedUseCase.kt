package com.example.domain.usecases.auth

import com.example.domain.repository.AuthRepository

class IsUserAuthenticatedUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}