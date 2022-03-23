package com.example.domain.auth_usecases

import com.example.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, name: String, mobile_no: String) = repository.register(email, password, name, mobile_no)
}