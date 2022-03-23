package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.NoteRepositoryImpl
import com.example.domain.auth_usecases.*
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.NoteRepository
import com.example.domain.usecases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAuthRepository(
    ): AuthRepository = AuthRepositoryImpl(Firebase.auth)

    @Provides
    fun providesNotesRepository(
        notesRef: CollectionReference,
        notesQuery: Query
    ): NoteRepository = NoteRepositoryImpl(notesRef, notesQuery)


    @Provides
    fun provideUseCases(repository: AuthRepository) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository),
        signIn = SignInUseCase(repository),
        signOut = SignOut(repository),
        register = RegisterUseCase(repository),
        getAuthState = GetAuthState(repository)
    )

    @Provides
    @Singleton
    fun provideNotesUseCase(repository: NoteRepository): NotesUseCase {
        return NotesUseCase(
            createNote = CreateNoteUseCase(repository),
            readNote = ReadNoteUseCase(repository),
            readNoteById = ReadNoteByIdUseCase(repository),
            updateNote = UpdateNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository)
        )
    }

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideNotesRef(db: FirebaseFirestore) = db.collection("Notes")

    @Provides
    fun provideNotesQuery(notessRef: CollectionReference) = notessRef.orderBy("Title")

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}