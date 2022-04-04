package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean
    suspend fun signIn(email: String, password: String): Flow<Any>
    suspend fun signOut(): Flow<Any>
    suspend fun register(email: String, password: String, name: String, mobile_no: String): Flow<Any>
//    suspend fun biometrics(): Boolean
    fun getFirebaseAuthState(): Flow<Boolean>
}