package com.example.keepnotes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.models.Note
import com.example.keepnotes.components.*
import com.example.keepnotes.viewmodel.AuthViewModel
import com.example.keepnotes.viewmodel.NotesViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.net.NoRouteToHostException

@Destination
@OptIn(ExperimentalMaterial3Api::class, kotlinx.coroutines.InternalCoroutinesApi::class)
@Composable
fun MainScreen(
    notesViewModel: NotesViewModel = hiltViewModel()
//    note: Note
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val notes = notesViewModel.notessData

    Scaffold(
        topBar = {
            TopNavBar(
                drawerState = drawerState,
                onClick = Firebase.auth.signOut()
            )
        },
        floatingActionButton = { FabBtn(notesViewModel) },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomNavBar() }
    ) {
        if(notesViewModel.openDialogState.value) {
            AddNoteAlertDialog()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            StaggeredGrid(note = notes.value)
        }
    }
}