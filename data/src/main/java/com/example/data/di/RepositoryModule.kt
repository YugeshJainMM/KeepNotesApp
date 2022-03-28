package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.NoteRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepository(
    ): AuthRepository = AuthRepositoryImpl(Firebase.auth)

    @Provides
    @Singleton
    fun providesNotesRepository(
        notesRef: CollectionReference,
        notesQuery: Query
    ): NoteRepository = NoteRepositoryImpl(notesRef, notesQuery)

}