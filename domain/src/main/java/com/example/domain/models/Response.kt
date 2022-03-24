package com.example.domain.models

sealed class Response<out T> {
    object Empty: Response<Nothing>()

    object Loading: Response<Nothing>()

    data class Success<out T>(
        val data: T
    ): Response<T>()

    data class Error(
        val message: String
    ): Response<Nothing>()
}