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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopNavBar(drawerState = drawerState) },
        floatingActionButton = { FabBtn() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomNavBar() },
        drawerContent = { NavDrawer(drawerState = drawerState) }
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