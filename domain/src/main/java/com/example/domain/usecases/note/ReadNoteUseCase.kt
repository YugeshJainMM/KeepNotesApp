package com.example.domain.usecases.note

import com.example.domain.repository.NoteRepository

class ReadNoteUseCase(
    private val noteRepository: NoteRepository
) {
    operator fun invoke() = noteRepository.readNote()
}