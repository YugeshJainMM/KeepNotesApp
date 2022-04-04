package com.example.keepnotes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.keepnotes.components.*
import com.example.keepnotes.viewmodel.NotesViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalMaterial3Api::class, kotlinx.coroutines.InternalCoroutinesApi::class)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator,
    notesViewModel: NotesViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val notes = notesViewModel.notessData

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopNavBar(
                drawerState = drawerState,
                onClick = Firebase.auth.signOut(),
                navigator = navigator
            )
        },
        floatingActionButton = { FabBtn(navigator) },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomNavBar(navigator) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            StaggeredGrid(note = notes.value)
        }
    }
}