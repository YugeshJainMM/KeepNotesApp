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
import com.example.keepnotes.components.*
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val scaffoldState = rememberScaffoldState(drawerState)

    Scaffold(
        topBar = { TopNavBar(drawerState = drawerState) },
        floatingActionButton = { FabBtn() },
//        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = { BottomNavBar() },
//        drawerContent = { NavDrawer(drawerState = drawerState) }
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