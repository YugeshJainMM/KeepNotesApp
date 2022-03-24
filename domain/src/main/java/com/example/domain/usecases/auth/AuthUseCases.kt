package com.example.domain.usecases.auth

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticatedUseCase,
    val signIn: SignInUseCase,
    val signOut: SignOutUseCase,
    val register: RegisterUseCase,
    val getAuthState: GetAuthStateUseCase
)