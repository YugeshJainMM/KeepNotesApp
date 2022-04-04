package com.example.data.di

import androidx.biometric.BiometricPrompt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BiometricsModule {

    @Provides
    fun provideExecutor() = Executor {}

////    @Provides
//    fun provideBiometricPrompt() = BiometricPrompt()
//
//    @Provides
//    fun providesPromptInfo() = BiometricPrompt.PromptInfo
}