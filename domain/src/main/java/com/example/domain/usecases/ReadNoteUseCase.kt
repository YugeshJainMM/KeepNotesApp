package com.example.domain.usecases

import com.example.domain.models.Note
import com.example.domain.repository.NoteRepository

class ReadNoteUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke() = noteRepository.readNote()
}