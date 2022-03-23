package com.example.domain.auth_usecases

data class AuthUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val signIn: SignInUseCase,
    val signOut: SignOut,
    val register: RegisterUseCase,
    val getAuthState: GetAuthState
)