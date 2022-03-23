package com.example.keepnotes.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Note
import com.example.domain.models.Response
import com.example.domain.usecases.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesUseCase: NotesUseCase): ViewModel() {

    var notesState: Response<List<Note>> by mutableStateOf(Response.Loading)
        private set

    var isNoteAddedState: Response<Void?> by mutableStateOf(Response.Success(null))
        private set
    var openDialogState = mutableStateOf(false)

    var isNoteUpdatedState: Response<Void?> by mutableStateOf(Response.Success(null))
        private set

    var isNoteDeletedState: Response<Void?> by mutableStateOf(Response.Success(null))
        private set

    init {
        getNotes()
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun getNotes() {
        viewModelScope.launch {
            notesUseCase.readNote().collect{

            }
        }
    }

    fun createNote(title: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesUseCase.createNote(title, content).collect {
                isNoteAddedState = it
            }
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            notesUseCase.deleteNote(noteId).collect {
                isNoteDeletedState = it
            }
        }
    }

    fun updateNote(noteId: String, title: String, content: String) {
        viewModelScope.launch {
            notesUseCase.updateNote(noteId, title, content)
        }
    }
}