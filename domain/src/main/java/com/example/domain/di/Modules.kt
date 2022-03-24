package com.example.domain.di

import com.example.domain.auth_usecases.*
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.NoteRepository
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton
import javax.management.Query

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object Modules{
}