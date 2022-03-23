package com.example.domain.auth_usecases

import com.example.domain.repository.AuthRepository

class SignOut(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.signOut()
}