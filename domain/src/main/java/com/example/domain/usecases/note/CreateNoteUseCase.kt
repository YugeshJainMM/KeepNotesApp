package com.example.domain.usecases.note

import com.example.domain.repository.NoteRepository

class CreateNoteUseCase(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(title: String, content: String) =
        noteRepository.createNote(title, content)
}