package com.example.data.repository

import com.example.domain.models.Note
import com.example.domain.models.Response
import com.example.domain.repository.NoteRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class NoteRepositoryImpl @Inject constructor(
    private val notesRef: CollectionReference,
    private val notesQuery: Query
) : NoteRepository {

    override suspend fun createNote(title: String, content: String) = flow {
        try {
            emit(Response.Loading)
            val noteId = notesRef.document().id
            val note = Note(
                id = noteId,
                title = title,
                content = content
            )
            val addition = notesRef.document(noteId).set(note).await()
            emit(Response.Success(addition))
        } catch (e: Exception) {
            throw Error(e.message ?: e.toString())
        }
    }

    override fun readNote(): Flow<Response<List<Note>>> {
        TODO("Not yet implemented")
    }

    override fun readNoteById(noteId: String): Note? {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(
        noteId: String,
        title: String,
        content: String
    ): Flow<Response<Void?>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(noteId: String) = flow {
        try {
            emit(Response.Loading)
            val deletion = notesRef.document(noteId).delete().await()
            emit(Response.Success(deletion))
        } catch (e: Exception) {
            throw Error(e.message ?: e.toString())
        }
    }
}