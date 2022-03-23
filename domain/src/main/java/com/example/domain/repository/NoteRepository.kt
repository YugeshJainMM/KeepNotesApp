package com.example.domain.repository

import com.example.domain.models.Note
import com.example.domain.models.Response
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun createNote(title: String, content: String): Flow<Response<Void?>>
    fun readNote(): Flow<Response<List<Note>>>
    fun readNoteById(noteId: String): Note?
    suspend fun updateNote(noteId: String, title: String, content: String): Flow<Response<Void?>>
    suspend fun deleteNote(noteId: String): Flow<Response<Void?>>
}