package com.example.domain.usecases.auth

import com.example.domain.repository.AuthRepository

class SignOutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.signOut()
}