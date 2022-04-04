package com.example.keepnotes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.keepnotes.R
import com.example.keepnotes.destinations.MainScreenDestination
import com.example.keepnotes.ui.theme.whiteBackground
import com.example.keepnotes.viewmodel.NotesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AddNoteScreen(
    navigator: DestinationsNavigator, notesViewModel: NotesViewModel = hiltViewModel(),

    ) {
    Scaffold(
        topBar = {},
        bottomBar = {

        }
    ) {

        var title by remember { mutableStateOf("") }
        var content by remember { mutableStateOf("") }
        val focusRequester = FocusRequester()

        Column {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = {
                        Text(
                            text = "Title"
                        )
                    },
                    maxLines = 1,
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
            Column(
                verticalArrangement = Bottom,
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                BottomAppBar(
                    containerColor = whiteBackground,
                    content = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {
                                notesViewModel.createNote(title, content)
                                navigator.navigate(MainScreenDestination)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_outline_checkbox),
                                    contentDescription = "Create Note",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                            IconButton(onClick = {
                                notesViewModel.createNote(title, content)
                                navigator.navigate(MainScreenDestination)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_deleted),
                                    contentDescription = "Create Note",
                                    modifier = Modifier.size(25.dp)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}