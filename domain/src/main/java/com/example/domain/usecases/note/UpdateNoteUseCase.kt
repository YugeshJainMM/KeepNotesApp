package com.example.domain.usecases.note

import com.example.domain.repository.NoteRepository

class UpdateNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: String, title: String, content: String) = noteRepository.updateNote(noteId, title, content)
}