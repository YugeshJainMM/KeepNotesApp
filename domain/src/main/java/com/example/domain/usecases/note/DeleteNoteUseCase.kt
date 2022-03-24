package com.example.domain.usecases.note

import com.example.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: String) = noteRepository.deleteNote(noteId)
}