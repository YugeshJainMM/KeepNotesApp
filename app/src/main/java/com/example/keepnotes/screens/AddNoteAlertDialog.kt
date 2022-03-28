package com.example.keepnotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.keepnotes.viewmodel.NotesViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
@InternalCoroutinesApi
fun AddNoteAlertDialog(
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    if (notesViewModel.openDialogState.value) {
        AlertDialog(
            onDismissRequest = {
                notesViewModel.openDialogState.value = false
            },
            title = {
                Text(
                    text = "Add Note"
                )
            },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text(
                                text = "Note Title"
                            )
                        },
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                    DisposableEffect(Unit) {
                        focusRequester.requestFocus()
                        onDispose { }
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    TextField(
                        value = content,
                        onValueChange = { content = it },
                        placeholder = {
                            Text(
                                text = "Content"
                            )
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        notesViewModel.openDialogState.value = false
                        notesViewModel.createNote(title, content)
                    }
                ) {
                    Text(
                        text = "Add"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        notesViewModel.openDialogState.value = false
                    }
                ) {
                    Text(
                        text = "Dismiss"
                    )
                }
            }
        )
    }
}