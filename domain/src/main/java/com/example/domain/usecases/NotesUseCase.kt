package com.example.domain.usecases

data class NotesUseCase(
    val createNote: CreateNoteUseCase,
    val readNote: ReadNoteUseCase,
    val readNoteById: ReadNoteByIdUseCase,
    val updateNote: UpdateNoteUseCase,
    val deleteNote: DeleteNoteUseCase
)