package com.example.domain.usecases

import com.example.domain.repository.NoteRepository

class ReadNoteByIdUseCase(
    private val noteRepository: NoteRepository
) {
    fun invoke(noteId: String) = noteRepository.readNoteById(noteId)
}