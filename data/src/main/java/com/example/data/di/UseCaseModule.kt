package com.example.data.di

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.NoteRepository
import com.example.domain.usecases.auth.*
import com.example.domain.usecases.note.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideUseCases (repository: AuthRepository) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticatedUseCase(repository),
        signIn = SignInUseCase(repository),
        signOut = SignOutUseCase(repository),
        register = RegisterUseCase(repository),
        getAuthState = GetAuthStateUseCase(repository)
    )

    @Provides
    @ViewModelScoped
    fun provideNotesUseCase(repository: NoteRepository): NotesUseCase {
        return NotesUseCase(
            createNote = CreateNoteUseCase(repository),
            readNote = ReadNoteUseCase(repository),
            readNoteById = ReadNoteByIdUseCase(repository),
            updateNote = UpdateNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository)
        )
    }

}