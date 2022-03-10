package com.example.keepnotes.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.keepnotes.components.*

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { TopNavBar(scaffoldState = scaffoldState) },
        floatingActionButton = { FabBtn() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomNavBar() },
        drawerContent = { NavDrawer(scaffoldState = scaffoldState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            StaggeredGrid()
        }
    }

}