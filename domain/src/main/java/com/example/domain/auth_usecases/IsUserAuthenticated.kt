package com.example.domain.auth_usecases

import com.example.domain.repository.AuthRepository

class IsUserAuthenticated(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}